package com.sicon.tba.handler;

import com.sicon.tba.dto.TbaProvinciaDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaProvincia;
import com.sicon.tba.service.TbaProvinciaService;
import com.sicon.tba.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Clase que ccntiene los servicios de las provincias
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaProvinciaHandler {

    private final TbaProvinciaService tbaProvinciaService;
    private final ObjectValidator objectValidator;

    /**
     * Método que crea una provincia
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de la provincia creada
     */
    public Mono<ServerResponse> create(ServerRequest request){
        Mono<TbaProvinciaDto> tbaProvinciaDtoMono = request.bodyToMono(TbaProvinciaDto.class).doOnNext(objectValidator::validate).log();
        return tbaProvinciaDtoMono.flatMap(tbaProvinciaDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProvinciaService.createProvince(tbaProvinciaDto), TbaProvincia.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza una provincia por su id
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de la provincia actualizada
     */
    public Mono<ServerResponse> update(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<TbaProvinciaDto> tbaProvinciaDtoMono = request.bodyToMono(TbaProvinciaDto.class)
                .doOnNext(a-> a.setIdProvincia(id)).doOnNext(objectValidator::validate).log();
        return tbaProvinciaDtoMono.flatMap(tbaProvinciaDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProvinciaService.updateProvince(id, tbaProvinciaDto), TbaProvincia.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que consulta una provincia por su id
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de la provincia consultada
     */
    public Mono<ServerResponse> getById(ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProvinciaService.getProvinceById(id), TbaProvincia.class)
        .onErrorMap(e -> {
            log.error(e.getMessage());
            return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        });
    }

    /**
     * Método que consulta las provincias por parámetros
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de las provincias consultadas
     */
    public Mono<ServerResponse> getByParams(ServerRequest request){
        String countryIdParam = request.queryParam("countryId").orElse(null);
        String provinceName = request.queryParam("provinceName").orElse(null);
        String codRegion = request.queryParam("codRegion").orElse(null);
        Integer countryId = countryIdParam != null ? Integer.parseInt(countryIdParam) : null;

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaProvinciaService.getProvinceByParams(countryId,provinceName, codRegion), TbaProvincia.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
