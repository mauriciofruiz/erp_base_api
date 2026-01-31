package erp.base.api.handler

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.UsuarioRolesDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Usuario
import erp.base.api.service.UsuarioService
import erp.base.api.utils.Paginacion
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UsuarioHandler(
    private val usuarioService: UsuarioService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun updatePassword(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(Usuario::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { usuario ->
                ServerResponse.ok().body(usuarioService.updateUserPassword(request.pathVariable("id").toInt(), usuario), Usuario::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun updateuser(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(UsuarioRolesDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok().body(usuarioService.updateUser(request.pathVariable("id").toInt(), dto), Usuario::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .body(usuarioService.getUserById(id), Usuario::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getUsersBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val nick = request.queryParam("nick").orElse(null)
        val status = request.queryParam("status").orElse(null)?.toBooleanStrictOrNull()
        val identification = request.queryParam("identification").orElse(null)
        val name = request.queryParam("name").orElse(null)
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val usuarioMono = usuarioService.getUsersBySearchParams(nick, status, identification, name, page, limit)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(usuarioMono, Paginacion::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun forgotPassword(request: ServerRequest): Mono<ServerResponse> {
        val nickName = request.queryParam("param").orElse(null)
        return if (nickName != null) {
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(usuarioService.forgotPassword(nickName), Usuario::class.java)
                .onErrorMap { e ->
                    log.error(e.message)
                    CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
                }
        } else {
            Mono.error(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.NICK_NAME_NOT_OBTAINED))
        }
    }

    fun getUsersRequestPurchase(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(usuarioService.getUsersRequestPurchase(), Usuario::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getUsersPurchaseOrder(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(usuarioService.getUsersPurchaseOrder(), Usuario::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getSalesUsers(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(usuarioService.getSalesUser(), Usuario::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
}
