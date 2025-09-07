package erp.base.api.handler;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.response.PersonaClienteResponse;
import erp.base.api.dto.response.PersonaProveedorResponse;
import erp.base.api.dto.TbaPersonaDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaPersona;
import erp.base.api.service.TbaClienteService;
import erp.base.api.service.TbaPersonaService;
import erp.base.api.service.TbaProveedorService;
import erp.base.api.utils.Paginacion;
import erp.base.api.utils.Utils;
import erp.base.api.validation.ObjectValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Clase que maneja las peticiones de persona
 */
@Component
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Person Management", description = "Person management endpoints")
public class TbaPersonaHandler {
    private final TbaPersonaService tbaPersonaService;
    private final ObjectValidator objectValidator;
    private final TbaProveedorService tbaProveedorService;
    private final TbaClienteService tbaClienteService;

    /**
     * Método que guarda una persona
     *
     * @param request request
     * @return ServerResponse
     */
    @Operation(summary = "Create person", description = "Creates a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person created successfully", 
                    content = @Content(schema = @Schema(implementation = TbaPersona.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<TbaPersonaDto> personMono = request.bodyToMono(TbaPersonaDto.class).doOnNext(objectValidator::validate).log();
        return personMono.flatMap(person ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(tbaPersonaService.createPerson(person), TbaPersona.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza una persona por Id
     *
     * @param request request
     * @return ServerResponse
     */
    @Operation(summary = "Update person", description = "Updates an existing person by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated successfully", 
                    content = @Content(schema = @Schema(implementation = TbaPersona.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaPersonaDto> personMono = request.bodyToMono(TbaPersonaDto.class).doOnNext(objectValidator::validate).log();
        return personMono.flatMap(person ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(tbaPersonaService.updatePerson(id, person), TbaPersona.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que retorna todas las personas
     *
     * @param request request con los parámetros de paginación
     * @return ServerResponse con la lista de personas
     */
    public Mono<ServerResponse> getPersonsBySearchParams(ServerRequest request) {
        String pageParam = request.queryParam("page").orElse("1");
        String limitParam = request.queryParam("limit").orElse("10");

        // Convertir los parámetros de cadena a valores numéricos
        Integer page = Integer.parseInt(pageParam);
        Integer limit = Integer.parseInt(limitParam);
        Mono<Paginacion<TbaPersona>> personsFlux = tbaPersonaService.getPersonsBySearchParams(page, limit);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(personsFlux, TbaPersona.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que retorna una persona por id
     *
     * @param request request
     * @return ServerResponse con la persona
     */
    public Mono<ServerResponse> getById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaPersona> personMono = tbaPersonaService.getPersonById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(personMono, TbaPersona.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    public Mono<ServerResponse>
    getPersonProvidersByProviderId(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.getProviderById(id), PersonaProveedorResponse.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getPersonClientByProviderId(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaClienteService.getClientById(id), PersonaProveedorResponse.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    public Mono<ServerResponse> getAllPersonProviders(ServerRequest request) {
        String identification = null;
        String completeName = null;
        String paramSearch = request.queryParam("param").orElse(null);
        try {
            assert paramSearch != null;
            if (Utils.validateNumbers(paramSearch))
                identification = paramSearch;
            else
                completeName = paramSearch;
        } catch (Exception e) {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER));
        }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaPersonaService.getAllPersonProviders(completeName, identification), PersonaProveedorResponse.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAllPersonClients(ServerRequest request) {
        String identification = null;
        String completeName = null;
        String paramSearch = request.queryParam("param").orElse(null);
        try {
            assert paramSearch != null;
            if (Utils.validateNumbers(paramSearch)) {
                identification = paramSearch;
            } else {
                completeName = paramSearch;
            }
        } catch (Exception e) {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER));
        }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaPersonaService.getAllPersonClients(completeName, identification), PersonaClienteResponse.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAllPeople(ServerRequest request) {
        String identification = null;
        String completeName = null;
        String paramSearch = request.queryParam("param").orElse(null);
        try {
            assert paramSearch != null;
            if (Utils.validateNumbers(paramSearch)) {
                identification = paramSearch;
            } else {
                completeName = paramSearch;
            }
        } catch (Exception e) {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER));
        }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaPersonaService.getAllPeople(completeName, identification), TbaPersonaDto.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
