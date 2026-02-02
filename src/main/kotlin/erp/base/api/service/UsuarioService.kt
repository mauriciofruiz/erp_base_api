package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.EmpleadoDto
import erp.base.api.dto.UsuarioRolesDto
import erp.base.api.enums.RolEnum
import erp.base.api.exception.CustomException
import erp.base.api.models.Empleado
import erp.base.api.models.Usuario
import erp.base.api.repository.UsuarioRepository
import erp.base.api.repository.custom.UsuarioCustom
import erp.base.api.utils.Paginacion
import erp.base.api.utils.Utils
import io.r2dbc.spi.Row
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.security.NoSuchAlgorithmException
import java.time.LocalDateTime

@Service
class UsuarioService(
    private val databaseClient: DatabaseClient,
    private val tbaUsuarioRepository: UsuarioRepository,
    private val tbaUsuarioCustom: UsuarioCustom,
    private val tbaRolUsuarioService: RolUsuarioService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun updateUserPassword(userId: Int, userResponse: Usuario): Mono<Usuario> {
        val userExists = tbaUsuarioRepository.existsByIdPersona(userId)
        return userExists.flatMap { exists ->
            val tbaUsuarioMono = tbaUsuarioRepository.findById(userId)
            tbaUsuarioMono.flatMap { user ->
                try {
                    user.passUsr = Utils.encriptKey(userResponse.passUsr!!)
                } catch (e: Exception) {
                    return@flatMap Mono.error<Usuario>(CustomException(HttpStatus.BAD_REQUEST, e.message!!))
                }
                tbaUsuarioRepository.save(user)
            }
        }
    }

    fun updateUser(userId: Int, userResponse: UsuarioRolesDto): Mono<Usuario> {
        val userExists = tbaUsuarioRepository.existsByIdPersona(userId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND + userId)))
        return userExists.flatMap {
            val tbaUsuarioMono = tbaUsuarioRepository.findById(userId)
                .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND + userId)))
            tbaUsuarioMono.flatMap { user ->
                validateUpdateFields(user, userResponse)
                tbaUsuarioRepository.save(user)
                    .flatMap { user1 ->
                        tbaRolUsuarioService.updateRolByUser(userResponse.detailRol ?: emptyList()).collectList()
                    }
                    .then(Mono.just(user))
                    .onErrorResume { e -> Mono.error(CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.message!!)) }
            }
        }
    }

    private fun validateUpdateFields(user: Usuario, userResponse: UsuarioRolesDto) {
        if (!userResponse.nickUsr.isNullOrEmpty()) {
            if (user.nickUsr != userResponse.nickUsr) {
                val isValidNic = validateNickName(userResponse.nickUsr!!).block()!!
                if (isValidNic) {
                    user.nickUsr = userResponse.nickUsr
                }
            }
        }
        userResponse.idSucursal?.let { user.idSucursal = it }
        userResponse.horaInicioUsr?.let { user.horaInicioUsr = it }
        userResponse.horaFinUsr?.let { user.horaFinUsr = it }
        user.estadoUsr = userResponse.estado
        if (userResponse.estado == false) {
            user.fechaInactivacionUsr = LocalDateTime.now()
        }
        userResponse.imgUsr?.let { user.imgUsr = it }
        userResponse.idCaja?.let { user.idCaja = it }
    }

    fun getUserById(userId: Int): Mono<Usuario> =
        tbaUsuarioRepository.findById(userId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND + userId)))

    fun getUsersBySearchParams(
        nick: String?,
        status: Boolean?,
        identification: String?,
        name: String?,
        page: Int,
        limit: Int
    ): Mono<Paginacion<Usuario>> {
        return try {
            val searchByNick = nick != null && identification == null && name == null
            val searchByIdentification = nick == null && identification != null && name == null
            val searchByName = nick == null && identification == null && name != null
            val searchByStatus = nick == null && identification == null && name == null
            val statusValue = status ?: true
            val pageable: Pageable = PageRequest.of(page - 1, limit)

            when {
                searchByNick -> {
                    val totalCount = tbaUsuarioRepository.countByNickUsrContainingAndEstadoUsr(nick!!, statusValue)
                    val usersFlux = tbaUsuarioRepository.findByNickUsrContainingAndEstadoUsr(nick, statusValue, pageable)
                    totalCount.flatMap { count -> usersFlux.collectList().map { data -> Paginacion(data, page, limit, count.toInt()) } }
                }
                searchByIdentification -> {
                    val totalCount = getCountByIdentificationAndState(identification!!, statusValue)
                    val usuariosFlux = getUsersByIdentificationAndState(identification, statusValue, page, limit)
                    totalCount.flatMap { count -> usuariosFlux.collectList().map { data -> Paginacion(data, page, limit, count.toInt()) } }
                }
                searchByName -> {
                    val totalCount = getCountByPersonNameAndStatus(name!!, statusValue)
                    val usersFlux = getUsersByPersonNameAndStatus(name, statusValue, page, limit)
                    totalCount.flatMap { count -> usersFlux.collectList().map { data -> Paginacion(data, page, limit, count.toInt()) } }
                }
                searchByStatus -> {
                    val totalCount = tbaUsuarioRepository.countByEstadoUsr(statusValue)
                    val usersFlux = tbaUsuarioRepository.findByEstadoUsr(statusValue, pageable)
                    totalCount.flatMap { count -> usersFlux.collectList().map { data -> Paginacion(data, page, limit, count.toInt()) } }
                }
                else -> {
                    val totalCount = tbaUsuarioRepository.count()
                    val usersFlux = tbaUsuarioRepository.findAllBy(pageable)
                    totalCount.flatMap { count -> usersFlux.collectList().map { data -> Paginacion(data, page, limit, count.toInt()) } }
                }
            }
        } catch (e: Exception) {
            Mono.error(CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.message!!))
        }
    }

    private fun mapRowToTbaUsuario(row: Row): Usuario = Usuario(
        idUsr = row.get("ID_USR", Integer::class.java)?.toInt(),
        idSucursal = row.get("ID_SUCURSAL", Integer::class.java)?.toInt(),
        idPersona = row.get("ID_PERSONA", Integer::class.java)?.toInt(),
        nickUsr = row.get("NICK_USR", String::class.java),
        passUsr = row.get("PASS_USR", String::class.java),
        fechaIngresoUsr = row.get("FECHAINGRESO_USR", LocalDateTime::class.java),
        fechaInactivacionUsr = row.get("FECHAINACTIVACION_USR", LocalDateTime::class.java),
        horaInicioUsr = row.get("HORAINICIO_USR", String::class.java),
        horaFinUsr = row.get("HORAFIN_USR", String::class.java),
        tiempoUsr = row.get("TIEMPO_USR", Integer::class.java)?.toInt(),
        diasmaximoUsr = null,
        codigoDesbloqueoUsr = row.get("CODDESBLOQUEO_USR", String::class.java),
        imgUsr = row.get("IMG_USR", String::class.java),
        estadoUsr = row.get("ESTADO_USR", Boolean::class.javaObjectType),
        idCaja = row.get("id_caja", Integer::class.java)?.toInt()
    )

    private fun getCountByIdentificationAndState(identification: String, status: Boolean): Mono<Long> =
        databaseClient.sql(
            "SELECT COUNT(*) FROM USUARIO " +
                "WHERE ID_PERSONA IN (SELECT ID_PERSONA FROM PERSONA " +
                "WHERE IDENTIFICACION_PERSONA LIKE :identification) AND ESTADO_USR = :status"
        )
            .bind("identification", "%$identification%")
            .bind("status", status)
            .map { row, _ -> row.get(0, Long::class.javaObjectType)!! }
            .one()

    private fun getUsersByIdentificationAndState(
        identification: String,
        status: Boolean,
        page: Int,
        limit: Int
    ): Flux<Usuario> =
        databaseClient.sql(
            "SELECT * FROM USUARIO " +
                "WHERE ID_PERSONA IN (SELECT ID_PERSONA FROM PERSONA " +
                "WHERE IDENTIFICACION_PERSONA LIKE :identification) AND ESTADO_USR = :status " +
                "LIMIT :limit OFFSET :offset"
        )
            .bind("identification", "%$identification%")
            .bind("status", status)
            .bind("limit", limit)
            .bind("offset", (page - 1) * limit)
            .map { row, _ -> mapRowToTbaUsuario(row) }
            .all()

    private fun getCountByPersonNameAndStatus(name: String, status: Boolean): Mono<Long> =
        databaseClient.sql(
            "SELECT COUNT(*) FROM USUARIO " +
                "WHERE ID_PERSONA IN (SELECT ID_PERSONA FROM PERSONA " +
                "WHERE NOMBRE_PERSONA LIKE :name) AND ESTADO_USR = :status"
        )
            .bind("name", "%$name%")
            .bind("status", status)
            .map { row, _ -> row.get(0, Long::class.javaObjectType)!! }
            .one()

    private fun getUsersByPersonNameAndStatus(
        name: String,
        status: Boolean,
        page: Int,
        limit: Int
    ): Flux<Usuario> =
        databaseClient.sql(
            "SELECT * FROM USUARIO " +
                "WHERE ID_PERSONA IN (SELECT ID_PERSONA FROM PERSONA " +
                "WHERE NOMBRE_PERSONA LIKE :name) AND ESTADO_USR = :status " +
                "LIMIT :limit OFFSET :offset"
        )
            .bind("name", "%$name%")
            .bind("status", status)
            .bind("limit", limit)
            .bind("offset", (page - 1) * limit)
            .map { row, _ -> mapRowToTbaUsuario(row) }
            .all()

    fun createNickName(completeName: String, exists: Boolean, i: Int): String {
        val name = completeName.split(" ")
        var surname = ""
        var firstName = ""
        var secondName = ""
        if (name.size >= 1) surname = name[0]
        if (name.size >= 3) firstName = name[2]
        if (name.size >= 4) secondName = name[3]
        return when {
            exists && i == 1 -> "${firstName[0]}${secondName[0]}$surname"
            i == 2 -> "${firstName.take(1)}${secondName[0]}$surname"
            else -> "${firstName[0]}$surname"
        }
    }

    fun createUser(employee: Empleado, nickName: String, personId: Int, chargeId: Int): Mono<Usuario> =
        tbaUsuarioRepository.save(
            Usuario(
                idUsr = null,
                idSucursal = employee.idSucursal,
                idPersona = personId,
                nickUsr = nickName,
                passUsr = Utils.encriptKey("1234"),
                fechaIngresoUsr = LocalDateTime.now(),
                fechaInactivacionUsr = null,
                horaInicioUsr = null,
                horaFinUsr = null,
                tiempoUsr = null,
                diasmaximoUsr = null,
                codigoDesbloqueoUsr = null,
                imgUsr = null,
                estadoUsr = true,
                idCaja = null
            )
        )

    fun forgotPassword(nickName: String): Mono<Usuario> {
        val exists = tbaUsuarioRepository.existsByNickUsrIgnoreCase(nickName)
        return exists.flatMap { exist ->
            if (exist) {
                val tbaUsuarioMono = tbaUsuarioRepository.findByNickUsrIgnoreCase(nickName)
                tbaUsuarioMono.flatMap { user ->
                    try {
                        user.passUsr = Utils.encriptKey("1234")
                        tbaUsuarioRepository.save(user)
                    } catch (e: Exception) {
                        Mono.error<Usuario>(CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.PASS_NOT_GENERATE + e.message))
                    }
                }
            } else {
                Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND + nickName))
            }
        }
    }

    fun validateNickName(nickName: String): Mono<Boolean> =
        tbaUsuarioRepository.existsByNickUsrIgnoreCase(nickName)

    fun validateByIdPersona(personId: Int): Mono<Boolean> =
        tbaUsuarioRepository.existsByIdPersona(personId)

    fun getUsersRequestPurchase(): Flux<Usuario> =
        tbaUsuarioRepository.findUsersRequestPurchase(true)

    fun getUsersPurchaseOrder(): Flux<Usuario> =
        tbaUsuarioRepository.findUsersPurchaseOrder(true)

    fun getSalesUser(): Flux<Usuario> =
        tbaUsuarioCustom.getUserByRoleId(RolEnum.VENTAS.id)
}
