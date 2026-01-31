package erp.base.api.handler

import erp.base.api.exception.CustomException
import erp.base.api.models.SustentoTributario
import erp.base.api.service.SustentoTributarioService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class SustentoTributarioHandler(
    private val sustentoTributarioService: SustentoTributarioService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(sustentoTributarioService.getAllSustentoTributario(), SustentoTributario::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
}
