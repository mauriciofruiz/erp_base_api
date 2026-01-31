package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.exception.CustomException
import erp.base.api.models.TbaRol
import erp.base.api.models.TbaRolUsuario
import erp.base.api.models.Usuario
import erp.base.api.repository.RolUsuarioRepository
import erp.base.api.repository.UsuarioRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RolUsuarioService(
    private val tbaRolUsuarioRepository: RolUsuarioRepository,
    private val tbaUsuarioRepository: UsuarioRepository,
    private val tbaRolService: TbaRolService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun create(tbaRolUsuario: TbaRolUsuario): Mono<TbaRolUsuario> =
        validateAndSaveUserRole(null, tbaRolUsuario)

    fun update(id: Int, tbaRolUsuario: TbaRolUsuario): Mono<TbaRolUsuario> =
        validateAndSaveUserRole(id, tbaRolUsuario)

    private fun validateAndSaveUserRole(id: Int?, tbaRolUsuario: TbaRolUsuario): Mono<TbaRolUsuario> {
        val tbaRolUsuarioMono = if (id != null) tbaRolUsuarioRepository.findById(id) else Mono.just(TbaRolUsuario())
        val tbaUsuarioMono = tbaUsuarioRepository.findById(tbaRolUsuario.idUsuario!!)
        val tbaRolMono = tbaRolService.getRoleById(tbaRolUsuario.idRol!!)
        return Mono.zip(tbaRolUsuarioMono, tbaUsuarioMono, tbaRolMono).flatMap { tuple ->
            tuple.t1.idRolUsuario = id
            tuple.t1.idUsuario = tuple.t2.idUsr
            tuple.t1.idRol = tuple.t3.idRol
            tuple.t1.estadoRolUsuario = tbaRolUsuario.estadoRolUsuario
            tbaRolUsuarioRepository.save(tuple.t1)
        }
    }

    fun deleteUserRole(id: Int): Mono<Void> =
        tbaRolUsuarioRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ROLE_NOT_FOUND + id)))
            .flatMap(tbaRolUsuarioRepository::delete)

    fun findById(id: Int): Mono<TbaRolUsuario> =
        tbaRolUsuarioRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ROLE_NOT_FOUND + id)))

    fun findAll(): Flux<TbaRolUsuario> =
        tbaRolUsuarioRepository.findAll()

    fun findRolByUserId(id: Int): Flux<TbaRolUsuario> =
        tbaRolUsuarioRepository.findByIdUsuario(id)
            .switchIfEmpty(Flux.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ROLE_NOT_FOUND + id)))

    fun updateRolByUser(detailRol: List<TbaRolUsuario>): Flux<TbaRolUsuario> =
        tbaRolUsuarioRepository.saveAll(detailRol).onErrorMap { e ->
            log.error("Error al guardar los roles por usuario")
            CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
        }
}
