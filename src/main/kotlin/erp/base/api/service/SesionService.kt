package erp.base.api.service

import erp.base.api.constants.Constants
import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.SesionDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Sesion
import erp.base.api.repository.SesionRepository
import erp.base.api.repository.custom.SesionCustom
import erp.base.api.utils.Paginacion
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class SesionService(
    private val sesionRepository: SesionRepository,
    private val tbaSesionCustom: SesionCustom
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun createSesion(sesionDto: Sesion): Mono<Sesion> =
        sesionRepository.save(
            Sesion(
                idSesion = null,
                idUsr = sesionDto.idUsr,
                token = sesionDto.token,
                fechaCreacion = sesionDto.fechaCreacion,
                fechaExpiracion = sesionDto.fechaExpiracion,
                estado = sesionDto.estado,
                duracionMinutos = sesionDto.duracionMinutos
            )
        )

    fun updateSesion(id: Int, sesionDto: Sesion): Mono<Sesion> =
        sesionRepository.save(
            Sesion(
                idSesion = id,
                idUsr = sesionDto.idUsr,
                token = sesionDto.token,
                fechaCreacion = sesionDto.fechaCreacion,
                fechaExpiracion = sesionDto.fechaExpiracion,
                estado = sesionDto.estado,
                duracionMinutos = sesionDto.duracionMinutos
            )
        )

    fun getSessionByToken(token: String): Mono<Sesion> =
        sesionRepository.findByToken(token)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.SESSION_NOT_FOUND)))

    fun updateStatusSesion(id: Int): Mono<Sesion> =
        sesionRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.SESSION_NOT_FOUND + id)))
            .flatMap { sesion ->
                sesion.estado = false
                sesionRepository.save(sesion)
            }

    fun updateAllSesion(sesionList: List<SesionDto>): Flux<Sesion> =
        Flux.fromIterable(sesionList).flatMap { sesion ->
            sesionRepository.findById(sesion.idSesion!!)
                .flatMap { validSesion ->
                    validSesion.estado = false
                    sesionRepository.save(validSesion)
                }
                .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, "Sesion con ID " + sesion.idSesion + " no encontrada")))
        }.onErrorMap { e ->
            log.error("Error al actualizar las sesiones", e)
            CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
        }

    fun getRequestBySearchParams(
        userId: Int?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<SesionDto>> {
        val sesionFlux = tbaSesionCustom.getBySearchParams(userId, sortfield, sortDirection, limit, (page - 1) * limit)
        val total = tbaSesionCustom.getCountBySearchParams(userId)
        return total.flatMap { totalCount ->
            sesionFlux.collectList().map { sesionList -> Paginacion(sesionList, page, limit, totalCount.toInt()) }
        }
    }

    fun getAllSessions(): Flux<Sesion> =
        sesionRepository.findAll()

    fun getHeadersByUser(idUsr: Int): Mono<HttpHeaders> =
        sesionRepository.findByIdUsrAndEstado(idUsr, true).map { sesion ->
            HttpHeaders().apply {
                setBearerAuth(sesion.token!!)
                set(Constants.USER_ID_HEADER, sesion.idUsr.toString())
            }
        }
}
