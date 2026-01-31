package erp.base.api.handler

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.PersonaDto
import erp.base.api.dto.response.PersonaClienteResponse
import erp.base.api.dto.response.PersonaProveedorResponse
import erp.base.api.exception.CustomException
import erp.base.api.models.Persona
import erp.base.api.service.ClienteService
import erp.base.api.service.PersonaService
import erp.base.api.service.ProveedorService
import erp.base.api.utils.Paginacion
import erp.base.api.utils.Utils
import erp.base.api.validation.ObjectValidator
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
@Tag(name = "Person Management", description = "Person management endpoints")
class PersonaHandler(
    private val personaService: PersonaService,
    private val objectValidator: ObjectValidator,
    private val proveedorService: ProveedorService,
    private val clienteService: ClienteService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Operation(summary = "Create person", description = "Creates a new person")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Person created successfully", content = [Content(schema = Schema(implementation = Persona::class))]),
        ApiResponse(responseCode = "400", description = "Invalid input data"),
        ApiResponse(responseCode = "500", description = "Internal server error"),
    ])
    fun create(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(PersonaDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { person ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(personaService.createPerson(person), Persona::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }

    @Operation(summary = "Update person", description = "Updates an existing person by ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Person updated successfully", content = [Content(schema = Schema(implementation = Persona::class))]),
        ApiResponse(responseCode = "400", description = "Invalid input data"),
        ApiResponse(responseCode = "404", description = "Person not found"),
        ApiResponse(responseCode = "500", description = "Internal server error"),
    ])
    fun update(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return request.bodyToMono(PersonaDto::class.java)
            .doOnNext { objectValidator.validate(it) }
            .log()
            .flatMap { person ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(personaService.updatePerson(id, person), Persona::class.java)
            }
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getPersonsBySearchParams(request: ServerRequest): Mono<ServerResponse> {
        val page = request.queryParam("page").orElse("1").toInt()
        val limit = request.queryParam("limit").orElse("10").toInt()
        val personsMono = personaService.getPersonsBySearchParams(page, limit)
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(personsMono, Paginacion::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(personaService.getPersonById(id), Persona::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getPersonProvidersByProviderId(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(proveedorService.getProviderById(id), PersonaProveedorResponse::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getPersonClientByProviderId(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(clienteService.getClientById(id), PersonaProveedorResponse::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllPersonProviders(request: ServerRequest): Mono<ServerResponse> {
        val paramSearch = request.queryParam("param").orElse(null)
            ?: return Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER))
        val identification: String? = if (Utils.validateNumbers(paramSearch)) paramSearch else null
        val completeName: String? = if (!Utils.validateNumbers(paramSearch)) paramSearch else null
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(personaService.getAllPersonProviders(completeName, identification), PersonaProveedorResponse::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllPersonClients(request: ServerRequest): Mono<ServerResponse> {
        val paramSearch = request.queryParam("param").orElse(null)
            ?: return Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER))
        val identification: String? = if (Utils.validateNumbers(paramSearch)) paramSearch else null
        val completeName: String? = if (!Utils.validateNumbers(paramSearch)) paramSearch else null
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(personaService.getAllPersonClients(completeName, identification), PersonaClienteResponse::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }

    fun getAllPeople(request: ServerRequest): Mono<ServerResponse> {
        val paramSearch = request.queryParam("param").orElse(null)
            ?: return Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER))
        val identification: String? = if (Utils.validateNumbers(paramSearch)) paramSearch else null
        val completeName: String? = if (!Utils.validateNumbers(paramSearch)) paramSearch else null
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
            .body(personaService.getAllPeople(completeName, identification), PersonaDto::class.java)
            .onErrorMap { e ->
                log.error(e.message)
                CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.message ?: e.message ?: "")
            }
    }
}
