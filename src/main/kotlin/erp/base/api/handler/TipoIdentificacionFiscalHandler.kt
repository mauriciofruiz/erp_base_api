package erp.base.api.handler

import erp.base.api.dto.TipoIdentificacionFiscalDto
import erp.base.api.exception.CustomException
import erp.base.api.models.TipoIdentificacionFiscal
import erp.base.api.service.TipoIdentificacionFiscalService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class TipoIdentificacionFiscalHandler(
    private val trbTipoIdentificacionFiscalService: TipoIdentificacionFiscalService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createFiscalIdentificationType(serverRequest: ServerRequest): Mono<ServerResponse> =
        serverRequest.bodyToMono(TipoIdentificacionFiscalDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(trbTipoIdentificacionFiscalService.createFiscalIdentificationType(dto), TipoIdentificacionFiscal::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun updateFiscalIdentificationType(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")
        return serverRequest.bodyToMono(TipoIdentificacionFiscalDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(trbTipoIdentificacionFiscalService.updateFiscalIdentificationType(id, dto), TipoIdentificacionFiscal::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getFiscalIdentificationTypeById(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(trbTipoIdentificacionFiscalService.getFiscalIdentificationType(id), TipoIdentificacionFiscal::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllFiscalIdentificationType(serverRequest: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(trbTipoIdentificacionFiscalService.getAllFiscalIdentificationType(), TipoIdentificacionFiscal::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
}
