package erp.base.api.handler

import erp.base.api.exception.CustomException
import erp.base.api.models.TbaRolUsuario
import erp.base.api.service.RolUsuarioService
import erp.base.api.validation.ObjectValidator
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class RolUsuarioHandler(
    private val rolUsuarioService: RolUsuarioService,
    private val objectValidator: ObjectValidator,
) {

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(TbaRolUsuario::class.java)
            .doOnNext { objectValidator.validate(it) }
            .flatMap { rolUsuarioService.create(it) }
            .flatMap { tbaRolUsuario -> ServerResponse.ok().bodyValue(tbaRolUsuario) }
            .onErrorResume { Mono.error(CustomException(HttpStatus.BAD_REQUEST, it.message ?: "")) }

    fun update(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(TbaRolUsuario::class.java)
            .doOnNext { objectValidator.validate(it) }
            .flatMap { tbaRolUsuario ->
                rolUsuarioService.update(request.pathVariable("id").toInt(), tbaRolUsuario)
            }
            .flatMap { tbaRolUsuario -> ServerResponse.ok().bodyValue(tbaRolUsuario) }
            .onErrorResume { Mono.error(CustomException(HttpStatus.BAD_REQUEST, it.message ?: "")) }

    fun delete(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return rolUsuarioService.deleteUserRole(id).then(ServerResponse.noContent().build())
    }

    fun findById(request: ServerRequest): Mono<ServerResponse> =
        rolUsuarioService.findById(request.pathVariable("id").toInt())
            .flatMap { tbaRolUsuario -> ServerResponse.ok().bodyValue(tbaRolUsuario) }
            .onErrorResume { Mono.error(CustomException(HttpStatus.BAD_REQUEST, it.message ?: "")) }

    fun findAll(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().body(rolUsuarioService.findAll(), TbaRolUsuario::class.java)

    fun findRolByUserId(request: ServerRequest): Mono<ServerResponse> {
        val userId = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .body(rolUsuarioService.findRolByUserId(userId), TbaRolUsuario::class.java)
            .onErrorResume { Mono.error(CustomException(HttpStatus.BAD_REQUEST, it.message ?: "")) }
    }
}
