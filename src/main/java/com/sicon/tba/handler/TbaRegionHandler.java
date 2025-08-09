package com.sicon.tba.handler;

import com.sicon.tba.dto.TbaRegionDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaRegion;
import com.sicon.tba.service.TbaRegionService;
import com.sicon.tba.validation.ObjectValidator;
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
public class TbaRegionHandler {

    private final TbaRegionService tbaRegionService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<TbaRegionDto> tbaRegionDtoMono = request.bodyToMono(TbaRegionDto.class).doOnNext(objectValidator::validate).log();
        return tbaRegionDtoMono.flatMap(tbaRegionDto ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaRegionService.createRegion(tbaRegionDto), TbaRegion.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        Mono<TbaRegionDto> tbaRegionDtoMono = request.bodyToMono(TbaRegionDto.class).doOnNext(objectValidator::validate).log();
        return tbaRegionDtoMono.flatMap(tbaRegionDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaRegionService.updateRegion(id, tbaRegionDto), TbaRegion.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene una región por ID
     *
     * @param request petición
     * @return región
     */
    public Mono<ServerResponse> getRegionById(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaRegionService.getRegionById(id), TbaRegion.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene todas las regiones
     *
     * @param request petición
     * @return lista de regiones
     */

    public Mono<ServerResponse> getRegionByName(ServerRequest request){
        String nombreRegion = request.queryParam("regionName").orElse(null);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaRegionService.getRegionByName(nombreRegion), TbaRegion.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });

    }
}
