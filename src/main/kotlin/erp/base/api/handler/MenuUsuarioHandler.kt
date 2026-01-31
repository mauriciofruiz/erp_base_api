package erp.base.api.handler

import erp.base.api.dto.MenuUsuarioDto
import erp.base.api.exception.CustomException
import erp.base.api.models.MenuUsuario
import erp.base.api.service.MenuUsuarioService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class MenuUsuarioHandler(
    private val menuUsuarioService: MenuUsuarioService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(MenuUsuarioDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(menuUsuarioService.createUserMenu(dto), MenuUsuario::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(MenuUsuarioDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(menuUsuarioService.updateUserMenu(id, dto), MenuUsuario::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun updateByList(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToFlux(MenuUsuario::class.java)
            .collectList()
            .flatMap { menuUsuarioDtoList ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(menuUsuarioService.updateUserMenuByList(menuUsuarioDtoList), MenuUsuario::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(menuUsuarioService.getUserMenuById(id), MenuUsuario::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getByRequestParams(request: ServerRequest): Mono<ServerResponse> {
        val userIdParam = request.queryParam("userId").orElse(null)
        val menuIdParam = request.queryParam("menuId").orElse(null)
        val userId = userIdParam?.toIntOrNull()
        val menuId = menuIdParam?.toIntOrNull()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(menuUsuarioService.getUserMenusByParams(menuId, userId), MenuUsuario::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
