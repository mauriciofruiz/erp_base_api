package erp.base.api.handler

import erp.base.api.constants.ErrorMessage
import erp.base.api.exception.CustomException
import erp.base.api.models.ImpuestoDocumento
import erp.base.api.service.ImpuestoDocumentoService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class ImpuestoDocumentoHandler(private val impuestoDocumentoService: ImpuestoDocumentoService) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun findImpuestosByDocumento(request: ServerRequest): Mono<ServerResponse> {
        val idDocumento = request.queryParam("idDocumento").orElse(null)?.toLongOrNull()
        val idTipoDocumento = request.queryParam("idTipoDocumento").orElse(null)?.toIntOrNull()
        val idVoucher = request.queryParam("idVoucher").orElse(null)?.toIntOrNull()
        return when {
            idDocumento != null -> buildResponse(impuestoDocumentoService.impuestoDocumentoByIdDocumento(idDocumento), ImpuestoDocumento::class.java)
            idTipoDocumento != null && idVoucher != null -> buildResponse(impuestoDocumentoService.impuestoDocumentoByIdTipoDocumentoAndIdVoucher(idTipoDocumento, idVoucher), ImpuestoDocumento::class.java)
            else -> Mono.error(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.GENERIC_MISSING_PARAMETERS))
        }
    }

    private fun <T> buildResponse(flux: Flux<T>, clazz: Class<T>): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(flux.onErrorMap { handleError(it) }, clazz)

    private fun handleError(e: Throwable): Throwable {
        log.error(e.message)
        return CustomException(HttpStatus.BAD_REQUEST, e.cause?.message ?: e.message ?: "")
    }
}
