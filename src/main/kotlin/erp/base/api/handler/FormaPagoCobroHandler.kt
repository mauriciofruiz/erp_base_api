package erp.base.api.handler

import erp.base.api.dto.FormaPagoCobroDto
import erp.base.api.exception.CustomException
import erp.base.api.models.FormaPagoCobro
import erp.base.api.service.FormaPagoCobroService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class FormaPagoCobroHandler(
    private val tbaFormaPagoCobroService: FormaPagoCobroService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createPaymentCollectionMethod(serverRequest: ServerRequest): Mono<ServerResponse> =
        serverRequest.bodyToMono(FormaPagoCobroDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaFormaPagoCobroService.createPaymentCollectionMethod(dto), FormaPagoCobro::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun updatePaymentCollectionMethod(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")
        return serverRequest.bodyToMono(FormaPagoCobroDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaFormaPagoCobroService.updatePaymentCollectionMethod(id, dto), FormaPagoCobro::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getPaymentCollectionMethod(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaFormaPagoCobroService.getPaymentCollectionMethod(id), FormaPagoCobro::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllFormaPagoCobro(serverRequest: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaFormaPagoCobroService.getAllPaymentCollectionMethod(), FormaPagoCobro::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getRequestBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        if (request.queryParams().isEmpty()) {
            return getAllFormaPagoCobro(request)
        }
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaFormaPagoCobroService.getRequestBySearchParams(page, limit, sortDirection, sortField), FormaPagoCobro::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
