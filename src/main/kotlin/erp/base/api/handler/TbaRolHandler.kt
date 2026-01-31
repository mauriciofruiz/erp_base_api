package erp.base.api.handler

import erp.base.api.exception.CustomException
import erp.base.api.models.TbaRol
import erp.base.api.service.TbaRolService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class TbaRolHandler(
    private val service: TbaRolService,
    private val validator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createRole(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(TbaRol::class.java)
            .doOnNext { validator.validate(it) }
            .flatMap { rol ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(service.createRole(rol), TbaRol::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun updateRole(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(TbaRol::class.java)
            .flatMap { rol -> service.updateRole(id, rol) }
            .flatMap { role -> ServerResponse.ok().body(Mono.just(role), TbaRol::class.java) }
    }

    fun deleteRole(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return service.deleteRole(id).then(ServerResponse.noContent().build())
    }

    fun getRoleById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return service.getRoleById(id)
            .flatMap { role -> ServerResponse.ok().body(Mono.just(role), TbaRol::class.java) }
    }

    fun getAllRoles(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().body(service.getAllRoles(), TbaRol::class.java)

    fun getBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortField = request.queryParam("sortField").orElse(null)
        return if (request.queryParams().isEmpty()) {
            getAllRoles(request)
        } else {
            ServerResponse.ok()
                .body(service.getRequestBySearchParams(page, limit, sortDirection, sortField), TbaRol::class.java)
                .onErrorMap { e ->
                    log.error(e.message)
                    CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
                }
        }
    }
}
