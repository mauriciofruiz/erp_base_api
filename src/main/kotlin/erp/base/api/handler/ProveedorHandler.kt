package erp.base.api.handler

import erp.base.api.constants.Constants
import erp.base.api.dto.PersonaProveedorDto
import erp.base.api.dto.ProveedorPatchDto
import erp.base.api.dto.response.PersonaProveedorResponse
import erp.base.api.exception.CustomException
import erp.base.api.models.Proveedor
import erp.base.api.service.ProveedorService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ProveedorHandler(
    private val proveedorService: ProveedorService,
    private val validator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createProvider(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(PersonaProveedorDto::class.java)
            .doOnNext { validator.validate(it) }
            .onErrorMap { e -> CustomException(HttpStatus.BAD_REQUEST, e.cause?.message ?: e.message ?: "") }
            .flatMap { personaProveedor ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(
                        proveedorService.createProvider(personaProveedor)
                            .onErrorMap { e ->
                                log.error(e.message)
                                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
                            },
                        PersonaProveedorDto::class.java
                    )
            }

    fun getAllProvider(request: ServerRequest): Mono<ServerResponse> {
        val searchForeign = request.queryParam("searchForeign").orElse("false").toBoolean()
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(proveedorService.getProvidersBySearchParams(searchForeign, page, limit, sortDirection, sortField), Proveedor::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun updateProvider(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(PersonaProveedorDto::class.java)
            .doOnNext { validator.validate(it) }
            .onErrorMap { e -> CustomException(HttpStatus.BAD_REQUEST, e.cause?.message ?: e.message ?: "") }
            .flatMap { personProvider ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(proveedorService.updateProvider(id, personProvider), Proveedor::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun patchProvider(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(ProveedorPatchDto::class.java)
            .onErrorMap { e -> CustomException(HttpStatus.BAD_REQUEST, e.cause?.message ?: e.message ?: "") }
            .flatMap { provider ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(proveedorService.patchProvider(id, provider), Proveedor::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getProvidersNameOCPendings(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(proveedorService.getProvidersNameOCPending(), PersonaProveedorResponse::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getAllByListaPedidoAprobadas(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(proveedorService.getAllByListaPedidoAprobadas(), PersonaProveedorResponse::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getProvider(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        val userId = request.headers().firstHeader(Constants.USER_ID_HEADER)!!.toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(proveedorService.getProvider(id, userId), Proveedor::class.java)
            .onErrorMap { e ->
                log.error(Constants.GENERIC_ERROR_MESSAGE_WITH_CAUSE, e.cause?.message ?: e.message, e)
                CustomException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE)
            }
    }

    fun getProviderForImport(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(proveedorService.getProviderForImport(id), Proveedor::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getProviderByOrderListId(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(proveedorService.getProviderByOrderListId(id), Proveedor::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun verifyProviderExistsByIdentification(request: ServerRequest): Mono<ServerResponse> {
        val identification = request.pathVariable("id")
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(proveedorService.verifyProviderExistsByIdentification(identification), Proveedor::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getCarriers(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(proveedorService.getCarriers(), PersonaProveedorResponse::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
}
