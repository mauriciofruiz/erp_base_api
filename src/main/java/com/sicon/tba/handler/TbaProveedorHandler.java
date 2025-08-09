package com.sicon.tba.handler;

import com.sicon.tba.constants.Constants;
import com.sicon.tba.dto.PersonaProveedorDto;
import com.sicon.tba.dto.response.PersonaProveedorResponse;
import com.sicon.tba.dto.ProveedorPatchDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.Proveedor;
import com.sicon.tba.service.TbaProveedorService;
import com.sicon.tba.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;

import static com.sicon.tba.handler.TbaSucursalHandler.extractHeaders;

@Component
@AllArgsConstructor
@Log4j2
public class TbaProveedorHandler {

    private final TbaProveedorService tbaProveedorService;
    private final ObjectValidator validator;

    /**
     * Crear un nuevo proveedor
     *
     * @param request Objeto con los datos del proveedor
     * @return Objeto TbaProveedor creado
     */
    public Mono<ServerResponse> createProvider(ServerRequest request) {
        Mono<PersonaProveedorDto> personaProveedorMono = request.bodyToMono(PersonaProveedorDto.class).doOnNext(validator::validate).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        return personaProveedorMono.flatMap(personaProveedor ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.createProvider(personaProveedor)
                        .onErrorMap(e -> {
                            log.error(e.getMessage());
                            return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                        }), PersonaProveedorDto.class));
    }

    /**
     * Obtener todos los proveedores
     *
     * @param request Objeto con los parámetros de paginación
     * @return Lista de proveedores
     */
    public Mono<ServerResponse> getAllProvider(ServerRequest request) {
        Boolean searchForeign = Boolean.valueOf(request.queryParam("searchForeign").orElse("false"));
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.getProvidersBySearchParams(searchForeign, page, limit, sortDirection, sortField), Proveedor.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Actualizar un proveedor
     *
     * @param request Objeto con los datos del proveedor
     * @return Objeto TbaProveedor actualizado
     */
    public Mono<ServerResponse> updateProvider(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<PersonaProveedorDto> personaProveedorMono = request.bodyToMono(PersonaProveedorDto.class).doOnNext(validator::validate).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        return personaProveedorMono.flatMap(personProvider -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(tbaProveedorService.updateProvider(id, personProvider), Proveedor.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Actualizar un proveedor con los datos enviados
     *
     * @param request Objeto con los datos del proveedor
     * @return respuesta de la actualización
     */
    public Mono<ServerResponse> patchProvider(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<ProveedorPatchDto> proveedorPathDtoMono = request.bodyToMono(ProveedorPatchDto.class).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        return proveedorPathDtoMono.flatMap(provider -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(tbaProveedorService.patchProvider(id, provider), Proveedor.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método para obtener los proveedores con ordenes de compra pendientes
     *
     * @param request
     * @return Lista de proveedores con ordenes de compra pendientes
     */
    public Mono<ServerResponse> getProvidersNameOCPendings(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.getProvidersNameOCPending(), PersonaProveedorResponse.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAllByListaPedidoAprobadas(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.getAllByListaPedidoAprobadas(), PersonaProveedorResponse.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    /**
     * Método para obtener un proveedor por su id (ficha-proveedor)
     *
     * @param request
     * @return Proveedor
     */
    public Mono<ServerResponse> getProvider(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Integer userId = Integer.parseInt(Objects.requireNonNull(request.headers().firstHeader(Constants.USER_ID_HEADER)));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.getProvider(id, userId), Proveedor.class)
                .onErrorMap(e -> {
                    log.error(Constants.GENERIC_ERROR_MESSAGE_WITH_CAUSE, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e);
                    return new CustomException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE);
                });
    }

    /**
     * Método para obtener un proveedor por su id (importar-proveedor)
     *
     * @param request request con el id del proveedor
     * @return respuesta con el proveedor
     */
    public Mono<ServerResponse> getProviderForImport(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.getProviderForImport(id), Proveedor.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getProviderByOrderListId(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.getProviderByOrderListId(id), Proveedor.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    public Mono<ServerResponse> verifyProviderExistsByIdentification(ServerRequest request) {
        String identification = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.verifyProviderExistsByIdentification(identification), Proveedor.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getCarriers(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProveedorService.getCarriers(), PersonaProveedorResponse.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

}
