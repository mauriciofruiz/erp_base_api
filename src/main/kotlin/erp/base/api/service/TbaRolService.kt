package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.exception.CustomException
import erp.base.api.models.TbaRol
import erp.base.api.repository.TbaRolRepository
import erp.base.api.utils.Paginacion
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TbaRolService(private val tbaRolRepository: TbaRolRepository) {

    fun createRole(tbaRol: TbaRol): Mono<TbaRol> =
        tbaRolRepository.save(tbaRol)

    fun updateRole(id: Int, tbaRol: TbaRol): Mono<TbaRol> =
        tbaRolRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ROLE_NOT_FOUND + id)))
            .flatMap { role ->
                role.idRol = id
                role.nombreRol = tbaRol.nombreRol
                role.descripcionRol = tbaRol.descripcionRol
                role.horaInicioRol = tbaRol.horaInicioRol
                role.horaFinRol = tbaRol.horaFinRol
                role.tiempoRol = tbaRol.tiempoRol
                tbaRolRepository.save(role)
            }

    fun deleteRole(id: Int): Mono<Void> =
        tbaRolRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ROLE_NOT_FOUND + id)))
            .flatMap(tbaRolRepository::delete)

    fun getRoleById(id: Int): Mono<TbaRol> =
        tbaRolRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ROLE_NOT_FOUND + id)))

    fun getAllRoles(): Flux<TbaRol> =
        tbaRolRepository.findAll()

    fun getRequestBySearchParams(
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<TbaRol>> {
        val rolDtoFlux = tbaRolRepository.getBySearchParams(sortfield, sortDirection, limit, (page - 1) * limit)
        val total = tbaRolRepository.getCountBySearchParams()
        return total.flatMap { totalCount ->
            rolDtoFlux.collectList().map { rolDtoList -> Paginacion(rolDtoList, page, limit, totalCount.toInt()) }
        }
    }
}
