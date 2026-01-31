package erp.base.api.handler

import erp.base.api.dto.SesionDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Sesion
import erp.base.api.service.SesionService
import erp.base.api.utils.Paginacion
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class SesionHandler(
    private val sesionService: SesionService,
    private val validator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun updateStatusSesion(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return sesionService.updateStatusSesion(id)
            .flatMap { role -> ServerResponse.ok().body(Mono.just(role), Sesion::class.java) }
    }

    fun updateAllStatusSesion(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToFlux(SesionDto::class.java)
            .collectList()
            .flatMap { sesionList ->
                sesionService.updateAllSesion(sesionList)
                    .collectList()
                    .flatMap { updatedSesions -> ServerResponse.ok().bodyValue(updatedSesions) }
                    .onErrorResume(CustomException::class.java) { e ->
                        ServerResponse.status(e.status).bodyValue(e.message ?: "")
                    }
            }

    fun getAllSesiones(): Mono<ServerResponse> =
        ServerResponse.ok().body(sesionService.getAllSessions(), Sesion::class.java)

    fun getBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val userId = request.queryParam("userId").orElse(null)?.toIntOrNull()
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        return if (request.queryParams().isEmpty()) {
            getAllSesiones()
        } else {
            ServerResponse.ok()
                .body(sesionService.getRequestBySearchParams(userId, page, limit, sortDirection, sortField), Paginacion::class.java)
                .onErrorMap { e ->
                    log.error(e.message)
                    CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
                }
        }
    }
}
