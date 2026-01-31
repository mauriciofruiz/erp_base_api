package erp.base.api.handler

import erp.base.api.dto.BitacoraDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Bitacora
import erp.base.api.service.BitacoraService
import erp.base.api.utils.Paginacion
import erp.base.api.utils.Utils
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class BitacoraHandler(
    private val bitacoraService: BitacoraService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun save(request: ServerRequest): Mono<ServerResponse> {
        val clientIp = Utils.getClientIp(request)
        val clientHostname = Utils.getClientHostname(request)
        return request.bodyToMono(Bitacora::class.java)
            .doOnNext { objectValidator.validate(it) }
            .flatMap { bitacora ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(bitacoraService.saveTransactionLog(bitacora, clientIp, clientHostname), Bitacora::class.java)
            }
            .onErrorMap { e -> log.error(e.message); CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
    }

    fun findAllByIdAndTable(request: ServerRequest): Mono<ServerResponse> {
        val id = request.queryParam("id").orElse("1").toInt()
        val table = request.queryParam("table").orElse("Producto")
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(bitacoraService.findAllByIdAndTable(id, table), BitacoraDto::class.java)
            .onErrorMap { e -> log.error(e.message); CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
    }

    fun findTransactionLogByClient(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse("desc")
        val sortField = request.queryParam("sortField").orElse("date")
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(bitacoraService.getTransactionLogByClient(id, page, limit, sortDirection, sortField), Paginacion::class.java)
            .onErrorMap { e -> log.error(e.message); CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
    }
}
