package erp.base.api.handler

import erp.base.api.dto.SucursalDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Sucursal
import erp.base.api.service.SucursalService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.function.Consumer

@Component
class SucursalHandler(
    private val sucursalService: SucursalService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        @JvmStatic
        fun extractHeaders(request: ServerRequest): Consumer<HttpHeaders> =
            Consumer { headers ->
                headers.setBearerAuth(extractToken(request))
                request.headers().firstHeader("Host")?.let { host -> headers.set("Host", host) }
                headers.set("Origin", getClientIp(request))
            }

        @JvmStatic
        fun getClientIp(request: ServerRequest): String {
            val origin = request.headers().firstHeader("Origin")
            if (origin != null) return origin.split(",")[0].trim()
            return request.remoteAddress().get().hostString ?: ""
        }

        @JvmStatic
        fun extractToken(request: ServerRequest): String? =
            request.headers().header("Authorization").firstOrNull()?.replace("Bearer ", "")
    }

    fun create(request: ServerRequest): Mono<ServerResponse> {
        val headersConsumer = extractHeaders(request)
        return request.bodyToMono(SucursalDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok().body(sucursalService.createBranch(dto, headersConsumer), Sucursal::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val headersConsumer = extractHeaders(request)
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(SucursalDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok().body(sucursalService.updateBranch(id, dto, headersConsumer), Sucursal::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(sucursalService.getBranchById(id), Sucursal::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getByParams(request: ServerRequest): Mono<ServerResponse> {
        val cityId = request.queryParam("cityId").orElse(null)?.toIntOrNull()
        val regionId = request.queryParam("regionId").orElse(null)?.toIntOrNull()
        val categoryBranchIdParam = request.queryParam("categoryBranchId").orElse(null)
        val nameParam = request.queryParam("name").orElse(null)
        val mnemonicParam = request.queryParam("mnemonic").orElse(null)
        val isOutlets = request.queryParam("isOutlets").orElse(null)?.toBooleanStrictOrNull() ?: false
        val isActive = request.queryParam("isActive").orElse(null)?.toBooleanStrictOrNull() ?: false
        val isProduction = request.queryParam("isProduction").orElse(null)?.toBooleanStrictOrNull() ?: false
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(sucursalService.getBranchesByParams(cityId, regionId, categoryBranchIdParam, nameParam, mnemonicParam, isOutlets, isActive, isProduction), Sucursal::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllSucursales(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().body(sucursalService.getAllSucursales(), Sucursal::class.java)

    fun getRequestBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val cityId = request.queryParam("cityId").orElse(null)?.toIntOrNull()
        val regionId = request.queryParam("regionId").orElse(null)?.toIntOrNull()
        val categoryBranchIdParam = request.queryParam("categoryBranchId").orElse(null)
        val nameParam = request.queryParam("name").orElse(null)
        val mnemonicParam = request.queryParam("mnemonic").orElse(null)
        val isOutlets = request.queryParam("isOutlets").orElse(null)?.toBooleanStrictOrNull() ?: false
        val isProduction = request.queryParam("isProduction").orElse(null)?.toBooleanStrictOrNull() ?: false
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        if (request.queryParams().isEmpty()) return getAllSucursales(request)
        if (isProduction) {
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(sucursalService.getBranchesByParams(null, null, null, null, null, false, false, true), Sucursal::class.java)
                .onErrorMap { e ->
                    log.error(e.message)
                    CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
                }
        }
        return ServerResponse.ok()
            .body(sucursalService.getRequestBySearchParams(cityId, regionId, categoryBranchIdParam, nameParam, mnemonicParam, isOutlets, page, limit, sortDirection, sortField), Sucursal::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
