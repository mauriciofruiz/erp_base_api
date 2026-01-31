package erp.base.api.handler

import erp.base.api.dto.IdentificacionFiscalDto
import erp.base.api.exception.CustomException
import erp.base.api.models.IdentificacionFiscal
import erp.base.api.service.IdentificacionFiscalService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class IdentificacionFiscalHandler(
    private val identificacionFiscalService: IdentificacionFiscalService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(IdentificacionFiscalDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto -> ServerResponse.ok().body(identificacionFiscalService.createTaxIdentification(dto), IdentificacionFiscal::class.java) }
            .onErrorMap { e -> CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(IdentificacionFiscalDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto -> ServerResponse.ok().body(identificacionFiscalService.updateTaxIdentification(id, dto), IdentificacionFiscal::class.java) }
            .onErrorMap { e -> CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().body(identificacionFiscalService.getTaxIdentificationById(request.pathVariable("id").toInt()), IdentificacionFiscal::class.java)
            .onErrorMap { e -> CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().body(identificacionFiscalService.getAllTaxIdentification(), IdentificacionFiscal::class.java)
            .onErrorMap { e -> CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "") }
}
