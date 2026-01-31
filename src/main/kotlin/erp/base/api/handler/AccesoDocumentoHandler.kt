package erp.base.api.handler

import erp.base.api.exception.CustomException
import erp.base.api.models.AccesoDocumento
import erp.base.api.service.AccesoDocumentoService
import erp.base.api.validation.ObjectValidator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class AccesoDocumentoHandler(
    private val accesoDocumentoService: AccesoDocumentoService,
    private val objectValidator: ObjectValidator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(AccesoDocumento::class.java)
            .doOnNext { objectValidator.validate(it) }
            .flatMap { accesoDocumento ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(accesoDocumentoService.conditionalSave(accesoDocumento), AccesoDocumento::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun findByDocumentIdAndDocumentTypeIdAndName(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(AccesoDocumento::class.java)
            .doOnNext { objectValidator.validate(it) }
            .flatMap { accesoDocumento ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(accesoDocumentoService.findByDocumentAndTypeAndName(accesoDocumento.idDocumento!!, accesoDocumento.idTipoDocumento!!, accesoDocumento.nameUsuario!!), Void::class.javaObjectType)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(accesoDocumentoService.findAll(), AccesoDocumento::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    fun deleteByDocumentIdAndDocumentTypeId(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(AccesoDocumento::class.java)
            .doOnNext { objectValidator.validate(it) }
            .flatMap { accesoDocumento ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(accesoDocumentoService.deleteByDocumentAndTypeAndName(accesoDocumento.idDocumento!!, accesoDocumento.idTipoDocumento!!, accesoDocumento.nameUsuario!!), Void::class.javaObjectType)
            }

    fun findByDocumentIdAndDocumentTypeId(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(AccesoDocumento::class.java)
            .doOnNext { objectValidator.validate(it) }
            .flatMap { accesoDocumento ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(accesoDocumentoService.findByDocumentAndType(accesoDocumento.idDocumento!!, accesoDocumento.idTipoDocumento!!), AccesoDocumento::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
}
