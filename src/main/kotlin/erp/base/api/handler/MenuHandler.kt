package erp.base.api.handler

import erp.base.api.dto.MenuUsuarioResponseDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Menu
import erp.base.api.service.MenuService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class MenuHandler(
    private val tbaMenuService: MenuService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(MenuUsuarioResponseDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaMenuService.createMenu(dto), Menu::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(MenuUsuarioResponseDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaMenuService.updateMenu(id, dto), Menu::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaMenuService.getMenuById(id), Menu::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getBySearchParam(request: ServerRequest): Mono<ServerResponse> {
        val menuFatherId = request.queryParam("menuFatherId").orElse(null)?.toIntOrNull()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaMenuService.getBySearchParam(menuFatherId), Menu::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getRequestBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val menuId = request.queryParam("menuId").orElse(null)?.toIntOrNull()
        val menuName = request.queryParam("menuName").orElse(null)
        val menuLevel = request.queryParam("menuLevel").orElse(null)?.toIntOrNull()
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val sortDirection = request.queryParam("sortDirection").orElse(null)
        val sortfield = request.queryParam("sortField").orElse(null)

        if (request.queryParams().isEmpty()) {
            return getBySearchParam(request)
        }
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                tbaMenuService.getRequestBySearchParams(menuId, menuName, menuLevel, page, limit, sortDirection, sortfield),
                erp.base.api.utils.Paginacion::class.java,
            )
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
