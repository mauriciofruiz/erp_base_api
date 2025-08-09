package com.sicon.tba.handler;

import com.sicon.tba.dto.RrhAreaGrupoDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.rrh.RrhAreaGrupo;
import com.sicon.tba.service.RrhAreaGrupoService;
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
 * Clase que contiene los servicios de los grupos de áreas
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class RrhAreaGrupoHandler {

    public final RrhAreaGrupoService rrhAreaGrupoService;

    public final ObjectValidator objectValidator;

    /**
     * Método que crea un grupo de área
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos del grupo de área creado
     */
    public Mono<ServerResponse> create(ServerRequest request){
        Mono<RrhAreaGrupoDto> rrhAreaGrupoDtoMono = request.bodyToMono(RrhAreaGrupoDto.class).doOnNext(objectValidator::validate).log();
        return rrhAreaGrupoDtoMono.flatMap(rrhAreaGrupoDto->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(rrhAreaGrupoService.createGroupArea(rrhAreaGrupoDto), RrhAreaGrupoDto.class)).onErrorMap(e -> {
            log.error(e.getMessage());
            return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        });
    }

    /**
     * Método que obtiene un grupo de área por su id
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos del grupo de área
     */
    public Mono<ServerResponse> update(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<RrhAreaGrupoDto> rrhAreaGrupoDtoMono = request.bodyToMono(RrhAreaGrupoDto.class).doOnNext(objectValidator::validate).log();
        return rrhAreaGrupoDtoMono.flatMap(rrhAreaGrupoDto->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(rrhAreaGrupoService.updateGroupArea(id, rrhAreaGrupoDto), RrhAreaGrupo.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene un grupo de área por su id
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos del grupo de área
     */
    public Mono<ServerResponse> getById(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(rrhAreaGrupoService.getAreaGroupById(id), RrhAreaGrupo.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getByParams(ServerRequest request){
        String name = request.queryParam("name").orElse(null);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(rrhAreaGrupoService.getAreaGroupByParams(name), RrhAreaGrupo.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
