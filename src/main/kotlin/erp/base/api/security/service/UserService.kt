package erp.base.api.security.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.MenuUsuarioResponseDto
import erp.base.api.dto.RolResponseDto
import erp.base.api.exception.CustomException
import erp.base.api.models.AccesoDocumento
import erp.base.api.models.Persona
import erp.base.api.models.Sesion
import erp.base.api.repository.AccesoDocumentoRepository
import erp.base.api.repository.MenuUsuarioRepository
import erp.base.api.repository.PersonaRepository
import erp.base.api.repository.RolRepository
import erp.base.api.repository.SesionRepository
import erp.base.api.security.dto.LoginDto
import erp.base.api.security.dto.LoginResponseDto
import erp.base.api.security.jwt.JwtProvider
import erp.base.api.security.repository.UserRepository
import erp.base.api.utils.Utils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
    private val userRepository: UserRepository,
    private val menuUsuarioRepository: MenuUsuarioRepository,
    private val personaRepository: PersonaRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val sesionRepository: SesionRepository,
    private val accesoDocumentoRepository: AccesoDocumentoRepository,
    private val rolRepository: RolRepository,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun login(dto: LoginDto): Mono<LoginResponseDto> =
        userRepository.findByNickUsrIgnoreCaseAndEstadoUsr(dto.nick!!.trim(), true)
            .filter { user -> passwordEncoder.matches(dto.password, user.password) }
            .flatMap { user ->
                sesionRepository.existsSesionByIdUsrAndEstado(user.idUsr!!, true)
                    .flatMap { exists ->
                        if (exists) {
                            sesionRepository.findByIdUsrAndEstado(user.idUsr!!, true)
                                .flatMap { sesion ->
                                    val expiratedSession = sesion.fechaExpiracion!!.isBefore(Utils.currentZonedDateTimeEcuador())
                                    if (expiratedSession) {
                                        logout(sesion.token!!).then(
                                            Mono.error<LoginResponseDto>(CustomException(HttpStatus.BAD_REQUEST, "Sesión expirada, ingrese nuevamente"))
                                        )
                                    } else {
                                        Mono.error(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.USER_ALREADY_LOGGED_IN))
                                    }
                                }
                        } else {
                            val menuUserResponseMono = menuUsuarioRepository.findMenusByUserId(user.idUsr!!)
                                .collectList()
                                .onErrorResume { throwable ->
                                    log.error("Error occurred while fetching menu for user {}", user.idUsr, throwable)
                                    Mono.error<List<MenuUsuarioResponseDto>>(CustomException(HttpStatus.BAD_REQUEST, "Error al obtener el menú"))
                                }
                            val personMono = personaRepository.findById(user.idPersona!!)
                            val rolesMono = rolRepository.getActiveRolesByUser(user.idUsr!!)
                                .collectList()
                                .onErrorResume { throwable ->
                                    log.error("Error occurred while fetching roles for user {}", user.idUsr, throwable)
                                    Mono.error<List<RolResponseDto>>(CustomException(HttpStatus.BAD_REQUEST, "Error al obtener los roles"))
                                }
                            Mono.zip(menuUserResponseMono, personMono, rolesMono)
                                .flatMap { tuple ->
                                    val menuUserResponseList = tuple.t1
                                    val persona = tuple.t2
                                    val response = LoginResponseDto(
                                        userId = user.idUsr,
                                        userName = persona.nombrePersona,
                                        userNick = user.username,
                                        token = jwtProvider.generateToken(user),
                                        password = user.password,
                                        photo = user.imgUsr,
                                        menuList = menuUserResponseList,
                                        cashId = user.idCaja,
                                        branchId = user.idSucursal,
                                        roles = tuple.t3,
                                    )
                                    Mono.just(response)
                                }
                        }
                    }
            }
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.BAD_REQUEST, "Usuario o contraseña incorrectos")))

    fun logout(token: String): Mono<String> =
        sesionRepository.findByToken(token)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.SESSION_NOT_FOUND)))
            .flatMap { sesion ->
                userRepository.findById(sesion.idUsr!!)
                    .flatMap { usuario ->
                        accesoDocumentoRepository.findByNameUsuario(usuario.username!!)
                            .collectList()
                            .flatMap { accesoDocumentos: List<AccesoDocumento> ->
                                if (!accesoDocumentos.isNullOrEmpty()) {
                                    accesoDocumentoRepository.deleteAll(accesoDocumentos)
                                        .doOnSuccess { sesion.estado = false }
                                        .then(sesionRepository.save(sesion))
                                        .thenReturn("Logout exitoso")
                                } else {
                                    sesion.estado = false
                                    sesionRepository.save(sesion).thenReturn("Logout exitoso")
                                }
                            }
                    }
            }
            .onErrorResume { _: Throwable -> Mono.just("Logout Fallido") }
}
