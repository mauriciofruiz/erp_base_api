package erp.base.api.handler

import erp.base.api.exception.CustomException
import erp.base.api.service.ExcepcionesClienteDescuentoService
import erp.base.api.utils.Paginacion
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ExcepcionesClienteDescuentoHandler(private val excepcionesClienteDescuentoService: ExcepcionesClienteDescuentoService) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun delete(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return excepcionesClienteDescuentoService.delete(id).then(Mono.defer { ServerResponse.noContent().build() })
    }

    fun getByParams(request: ServerRequest): Mono<ServerResponse> {
        val clientId = request.queryParam("clientId").orElse(null)?.toIntOrNull()
        if (clientId == null) return ServerResponse.badRequest().bodyValue("El id del cliente es requerido")
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        return ServerResponse.ok().body(excepcionesClienteDescuentoService.getRequestsBySearchParams(clientId, page, limit, sortDirection, sortField), Paginacion::class.java)
            .onErrorMap { e -> log.error(e.message); CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
    }
}
