package erp.base.api.handler

import erp.base.api.dto.CategoriaSucursalDto
import erp.base.api.exception.CustomException
import erp.base.api.models.CategoriaSucursal
import erp.base.api.service.CategoriaSucursalService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class CategoriaSucursalHandler(
    private val tbaCategoriaSucursalService: CategoriaSucursalService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(CategoriaSucursalDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaCategoriaSucursalService.creatBranchCategory(dto), CategoriaSucursal::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")
        return request.bodyToMono(CategoriaSucursalDto::class.java)
            .doOnNext { it.idCategoriaSucursal = id }
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaCategoriaSucursalService.updateBranchCategory(id, dto), CategoriaSucursal::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaCategoriaSucursalService.getBranchCategoryId(id), CategoriaSucursal::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getByBranchParams(request: ServerRequest): Mono<ServerResponse> {
        val nombreCategoriaSucursal = request.queryParam("branchCategoryName").orElse(null)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaCategoriaSucursalService.getBranchCategoryName(nombreCategoriaSucursal), CategoriaSucursal::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
