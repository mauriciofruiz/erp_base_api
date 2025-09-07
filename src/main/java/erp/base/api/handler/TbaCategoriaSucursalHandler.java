package erp.base.api.handler;

import erp.base.api.dto.TbaCategoriaSucursalDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaCategoriaSucursal;
import erp.base.api.service.TbaCategoriaSucursalService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Log4j2
public class TbaCategoriaSucursalHandler {

    private final TbaCategoriaSucursalService tbaCategoriaSucursalService;
    private final ObjectValidator objectValidator;

    /**
     * Método para crear una categoria de sucursal
     *
     * @param request petición
     * @return Categoría Sucursal
     */

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<TbaCategoriaSucursalDto> tbaCategoriaSucursalDtoMono = request.bodyToMono(TbaCategoriaSucursalDto.class).doOnNext(objectValidator::validate).log();
        return tbaCategoriaSucursalDtoMono.flatMap(tbaCategoriaSucursalDto ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaCategoriaSucursalService.creatBranchCategory(tbaCategoriaSucursalDto), TbaCategoriaSucursal.class)
                )
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método para actualizar una categoria de sucursal
     *
     * @param request petición
     * @return Categoría Sucursal
     */

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<TbaCategoriaSucursalDto> tbaCategoriaSucursalDtoMono = request.bodyToMono(TbaCategoriaSucursalDto.class)
                .doOnNext(categoria -> categoria.setIdCategoriaSucursal(id)).doOnNext(objectValidator::validate).log();
        return tbaCategoriaSucursalDtoMono.flatMap(tbaCategoriaSucursalDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaCategoriaSucursalService.updateBranchCategory(id, tbaCategoriaSucursalDto), TbaCategoriaSucursal.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método para consultar una categoria de sucursal por ID
     *
     * @param request Objeto con los datos a petición
     * @return Objeto TbaCategoriaSucursal en base a los datos a consultar
     */

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaCategoriaSucursalService.getBranchCategoryId(id), TbaCategoriaSucursal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });

    }

    /**
     * Método para consultar una categoria de sucursal por parámetros
     *
     * @param request Objeto con los datos a petición
     * @return Objeto TbaCategoriaSucursal en base a los datos a consultar
     */

    public Mono<ServerResponse> getByBranchParams(ServerRequest request) {
        String nombreCategoriaSucursal = request.queryParam("branchCategoryName").orElse(null);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaCategoriaSucursalService.getBranchCategoryName(nombreCategoriaSucursal), TbaCategoriaSucursal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


}
