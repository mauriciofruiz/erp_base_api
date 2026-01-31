package erp.base.api.handler

import erp.base.api.dto.EstadoCivilDto
import erp.base.api.exception.CustomException
import erp.base.api.models.EstadoCivil
import erp.base.api.service.EstadoCivilService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class EstadoCivilHandler(
    private val tbaEstadoCivilService: EstadoCivilService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createMaritalStatus(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(EstadoCivilDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaEstadoCivilService.createMaritalStatus(dto), EstadoCivil::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun updateMaritalStatus(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(EstadoCivilDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaEstadoCivilService.updateMaritalStatus(id, dto), EstadoCivil::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getMaritalStatusById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaEstadoCivilService.getMaritalStatusById(id), EstadoCivil::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllMaritalStatus(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaEstadoCivilService.getAllMaritalStatus(), EstadoCivil::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
}
