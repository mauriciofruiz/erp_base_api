package com.sicon.tba.handler;

import com.sicon.tba.dto.TbaDepartamentoDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaDepartamento;
import com.sicon.tba.service.TbaDepartamentoService;
import com.sicon.tba.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los handlers de los departamentos
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaDepartamentoHandler {

    private final TbaDepartamentoService tbaDepartamentoService;

    private final ObjectValidator objectValidator;

    /**
     * Método que crea un departamento
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos del departamento creado
     */
    public Mono<ServerResponse> create(ServerRequest request){
        Mono<TbaDepartamentoDto> tbaDepartamentoDtoMono = request.bodyToMono(TbaDepartamentoDto.class).doOnNext(objectValidator::validate).log();
        return tbaDepartamentoDtoMono.flatMap(
                tbaDepartamentoDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                        body(tbaDepartamentoService.createDepartment(tbaDepartamentoDto), TbaDepartamento.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza un departamento por su id
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos del departamento
     */
    public Mono<ServerResponse> update(ServerRequest request){
        Integer id = Integer.parseInt(request.pathVariable("id"));
        Mono<TbaDepartamentoDto> tbaDepartamentoDtoMono = request.bodyToMono(TbaDepartamentoDto.class).doOnNext(objectValidator::validate).log();
        return tbaDepartamentoDtoMono.flatMap(
                tbaDepartamentoDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                        body(tbaDepartamentoService.updateDepartment(id, tbaDepartamentoDto), TbaDepartamento.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene un departamento por su id
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos del departamento
     */
    public Mono<ServerResponse> getById(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
                body(tbaDepartamentoService.getDepartmentById(id), TbaDepartamento.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene los departamentos por parámetros
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de los departamentos
     */
    public Mono<ServerResponse> getByParams(ServerRequest request){
       String nameParam = request.queryParam("name").orElse(null);
       String statusParam = request.queryParam("status").orElse(null);
       String areaGroupIdParam = request.queryParam("areaGroupId").orElse(null);

       Boolean status = statusParam != null ? Boolean.valueOf(statusParam) : null;
       Integer areaGroupId = areaGroupIdParam != null ? Integer.valueOf(areaGroupIdParam) : null;
       return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
               body(tbaDepartamentoService.getDepartmentsByParams(nameParam, status, areaGroupId), TbaDepartamento.class)
               .onErrorMap(e -> {
                   log.error(e.getMessage());
                   return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
               });

    }

    /**
     * Método que obtiene todos los departamentos
     * @param request petición
     * @return lista de cargos
     */

    public Mono<ServerResponse> getAllDepartment(ServerRequest request){
        Flux<TbaDepartamento> departamentoFlux = tbaDepartamentoService.getAllDepartment();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(departamentoFlux, TbaDepartamento.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
