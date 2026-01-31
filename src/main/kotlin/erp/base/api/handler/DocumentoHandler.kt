package erp.base.api.handler

import erp.base.api.dto.DocumentoDto
import erp.base.api.exception.CustomException
import erp.base.api.service.DocumentoService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class DocumentoHandler(
    private val documentoService: DocumentoService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun getRequestBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val documentNumber = request.queryParam("documentNumber").orElse(null)
        val documentType = request.queryParam("documentType").orElse(null)
        val name = request.queryParam("name").orElse(null)
        val identification = request.queryParam("identification").orElse(null)
        val accesKey = request.queryParam("accesKey").orElse(null)
        val isAuthorized = request.queryParam("isAuthorized").orElse("false").toBoolean()
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        return ServerResponse.ok()
            .body(documentoService.getRequestBySearchParams(documentNumber, documentType, name, identification, accesKey, isAuthorized, page, limit, sortDirection, sortField), DocumentoDto::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
