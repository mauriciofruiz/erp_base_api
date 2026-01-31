package erp.base.api.handler

import erp.base.api.dto.TipoContribuyenteDto
import erp.base.api.exception.CustomException
import erp.base.api.models.TipoContribuyente
import erp.base.api.service.TipoContribuyenteService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class TipoContribuyenteHandler(
    private val trbTipoContribuyenteService: TipoContribuyenteService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createContributorType(serverRequest: ServerRequest): Mono<ServerResponse> =
        serverRequest.bodyToMono(TipoContribuyenteDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(trbTipoContribuyenteService.createContributorType(dto), TipoContribuyente::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun updateContributorType(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")
        return serverRequest.bodyToMono(TipoContribuyenteDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { dto ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(trbTipoContribuyenteService.updateContributorType(id, dto), TipoContribuyente::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getContributorTypeById(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariable("id")
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(trbTipoContribuyenteService.getContributorTypeById(id), TipoContribuyente::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllContributorTypes(serverRequest: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(trbTipoContribuyenteService.getAllContributorTypes(), TipoContribuyente::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
}
