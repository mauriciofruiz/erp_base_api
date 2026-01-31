package erp.base.api.handler

import erp.base.api.exception.CustomException
import erp.base.api.models.Caja
import erp.base.api.service.CajaService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class CajaHandler(
    private val tbaCajaService: CajaService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun findCajaByIdSucusal(request: ServerRequest): Mono<ServerResponse> {
        val sucursalId = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaCajaService.findCajaByIdSucusal(sucursalId), Caja::class.java)
            .onErrorResume { error -> Mono.error(CustomException(HttpStatus.BAD_REQUEST, error.message ?: "")) }
    }

    fun findCajaById(request: ServerRequest): Mono<ServerResponse> {
        val cajaId = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaCajaService.findById(cajaId), Caja::class.java)
            .onErrorResume { error -> Mono.error(CustomException(HttpStatus.BAD_REQUEST, error.message ?: "")) }
    }

    fun getRequestBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val branchIdParam = request.queryParam("branchId").orElse(null)
        val branchId = if (branchIdParam.isNullOrEmpty()) null else branchIdParam.toIntOrNull()
        val nameParam = request.queryParam("name").orElse(null)
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaCajaService.getRequestBySearchParams(branchId, nameParam, page, limit, sortDirection, sortField), Caja::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(Caja::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { caja ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaCajaService.createCaja(caja), Caja::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(Caja::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { caja ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaCajaService.updateCaja(id, caja), Caja::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
