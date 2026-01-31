package erp.base.api.handler

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.ParroquiaDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Parroquia
import erp.base.api.service.ParroquiaService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ParroquiaHandler(
    private val tbaParroquiaService: ParroquiaService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createParish(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(ParroquiaDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaParroquiaService.createParish(dto), Parroquia::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun updateParish(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(ParroquiaDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaParroquiaService.updateParish(id, dto), Parroquia::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getParishById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaParroquiaService.getParishById(id), Parroquia::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllParishes(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaParroquiaService.getAllParishes(), Parroquia::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getParish(request: ServerRequest): Mono<ServerResponse> {
        val paramSearch = request.queryParam("param").orElse(null)
        val mono = if (paramSearch != null) {
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(tbaParroquiaService.getParish(paramSearch), ParroquiaDto::class.java)
                .onErrorMap { e ->
                    log.error(e.message)
                    CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
                }
        } else {
            getAllParishes(request)
        }
        return mono.onErrorResume { Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER)) }
    }

    fun getParishByCityId(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaParroquiaService.getParishByCityId(id), Parroquia::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
