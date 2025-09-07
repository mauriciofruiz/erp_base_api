package erp.base.api.handler;


import erp.base.api.dto.TbaSucursalDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Sucursal;
import erp.base.api.models.TbaRol;
import erp.base.api.service.TbaSucursalService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 * Clase que contiene los servicios de los grupos de áreas
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaSucursalHandler {

    private final TbaSucursalService tbaSucursalService;

    private final ObjectValidator objectValidator;

    public static Consumer<HttpHeaders> extractHeaders(ServerRequest request) {
        String ip = getClientIp(request);
        return headers -> {
            headers.setBearerAuth(extractToken(request));
            headers.setHost(request.headers().host());
            headers.set("Origin", ip);
        };
    }

    public static String getClientIp(ServerRequest request) {
        String origin = request.headers().firstHeader("Origin");
        if (origin != null) {
            // If the request has been forwarded, the first IP in the list is the original client IP
            return origin.split(",")[0].trim();
        }
        // If X-Forwarded-For header is not present, fall back to the remote address
        return request.remoteAddress().get().getHostString();
    }

    public static String extractToken(ServerRequest request) {
        return request.headers().header("Authorization")
                .stream()
                .findFirst()
                .map(header -> header.replace("Bearer ", ""))
                .orElse(null);
    }

    /**
     * Método que crea una surcursal
     *
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de la sucursal creada
     */
    public Mono<ServerResponse> create(ServerRequest request) {
        Consumer<HttpHeaders> headersConsumer = extractHeaders(request);
        Mono<TbaSucursalDto> tbaSucursaDtoMono = request.bodyToMono(TbaSucursalDto.class).doOnNext(objectValidator::validate).log();
        return tbaSucursaDtoMono.flatMap(tbaSucursalDto ->
                        ServerResponse.ok().body(tbaSucursalService.createBranch(tbaSucursalDto, headersConsumer), Sucursal.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza la información de una sucursal
     *
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de la sucursal actualizada
     */
    public Mono<ServerResponse> update(ServerRequest request) {
        Consumer<HttpHeaders> headersConsumer = extractHeaders(request);
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaSucursalDto> tbaSucursalMono = request.bodyToMono(TbaSucursalDto.class).doOnNext(objectValidator::validate).log();
        return tbaSucursalMono.flatMap(tbaSucursal ->
                        ServerResponse.ok().body(tbaSucursalService.updateBranch(id, tbaSucursal, headersConsumer), Sucursal.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que permite consulta una sucursal por su id
     *
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de la sucursal consultada
     */
    public Mono<ServerResponse> getById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaSucursalService.getBranchById(id), Sucursal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que permite consultar las sucursales por los parámetros
     *
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de las sucursales consultadas
     */
    public Mono<ServerResponse> getByParams(ServerRequest request) {
        Integer cityId = request.queryParam("cityId").map(Integer::valueOf).orElse(null);
        Integer regionId = request.queryParam("regionId").map(Integer::valueOf).orElse(null);
        String categoryBranchIdParam = request.queryParam("categoryBranchId").orElse(null);
        String nameParam = request.queryParam("name").orElse(null);
        String mnemonicParam = request.queryParam("mnemonic").orElse(null);
        Boolean isOutlets = Boolean.valueOf(request.queryParam("isOutlets").orElse(null));
        Boolean isActive = Boolean.valueOf(request.queryParam("isActive").orElse(null));
        Boolean isProduction = Boolean.valueOf(request.queryParam("isProduction").orElse(null));

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaSucursalService.getBranchesByParams(cityId, regionId, categoryBranchIdParam, nameParam, mnemonicParam, isOutlets, isActive, isProduction), Sucursal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAllSucursales(ServerRequest request) {
        return ServerResponse.ok().body(tbaSucursalService.getAllSucursales(), Sucursal.class);
    }

    public Mono<ServerResponse> getRequestBySearchParams(ServerRequest request) {
        Integer cityId = request.queryParam("cityId").map(Integer::valueOf).orElse(null);
        Integer regionId = request.queryParam("regionId").map(Integer::valueOf).orElse(null);
        String categoryBranchIdParam = request.queryParam("categoryBranchId").orElse(null);
        String nameParam = request.queryParam("name").orElse(null);
        String mnemonicParam = request.queryParam("mnemonic").orElse(null);
        Boolean isOutlets = Boolean.valueOf(request.queryParam("isOutlets").orElse(null));
        Boolean isProduction = Boolean.valueOf(request.queryParam("isProduction").orElse(null));
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        if (request.queryParams().isEmpty()) {
            return getAllSucursales(request);
        }

        if(isProduction){
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaSucursalService.getBranchesByParams(null, null, null, null, null, false, false, isProduction), Sucursal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
        }else{
            return ServerResponse.ok().body(tbaSucursalService.getRequestBySearchParams(cityId, regionId, categoryBranchIdParam, nameParam, mnemonicParam, isOutlets, page, limit, sortDirection, sortField), Sucursal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
        }
    }
}
