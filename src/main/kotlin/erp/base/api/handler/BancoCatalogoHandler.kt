package erp.base.api.handler

import erp.base.api.exception.CustomException
import erp.base.api.models.BancoCatalogo
import erp.base.api.service.BancoCatalogoService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class BancoCatalogoHandler(
    private val bancoCatalogoService: BancoCatalogoService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun getAllBankCatalog(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(bancoCatalogoService.getBankCatalog(), BancoCatalogo::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getCatalogBanksBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val nameCatalogBank = request.queryParam("bankCatalogName").orElse(null)
        val codeCatalog = request.queryParam("bankCatalogCode").orElse(null)
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        if (request.queryParams().isEmpty()) return getAllBankCatalog(request)
        return ServerResponse.ok()
            .body(bancoCatalogoService.getRequestBySearchParams(nameCatalogBank, codeCatalog, page, limit, sortDirection, sortField), BancoCatalogo::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(BancoCatalogo::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { bancoCatalogo ->
                ServerResponse.ok().body(bancoCatalogoService.createCatalogBank(bancoCatalogo), BancoCatalogo::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(BancoCatalogo::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { bancoCatalogo ->
                ServerResponse.ok().body(bancoCatalogoService.updateCatalogBank(id, bancoCatalogo), BancoCatalogo::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
