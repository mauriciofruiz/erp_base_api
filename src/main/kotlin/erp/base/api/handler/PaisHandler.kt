package erp.base.api.handler

import erp.base.api.dto.PaisDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Pais
import erp.base.api.service.PaisService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class PaisHandler(
    private val tbaPaisService: PaisService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createCountry(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(PaisDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaPaisService.createCountry(dto), Pais::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun updateCountry(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(PaisDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tbaPaisService.updateCountry(id, dto), Pais::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getCountryById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaPaisService.getCountryById(id), Pais::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllCountries(serverRequest: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(tbaPaisService.getAllCountries(), Pais::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
}
