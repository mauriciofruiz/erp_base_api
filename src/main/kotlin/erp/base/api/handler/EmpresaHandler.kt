package erp.base.api.handler

import erp.base.api.exception.CustomException
import erp.base.api.models.Empresa
import erp.base.api.service.EmpresaService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class EmpresaHandler(private val empresaService: EmpresaService) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun getCompany(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(empresaService.findById(request.pathVariable("id").toLong()), Empresa::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
}
