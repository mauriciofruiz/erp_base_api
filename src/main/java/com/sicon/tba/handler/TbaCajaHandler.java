package com.sicon.tba.handler;


import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.Caja;
import com.sicon.tba.models.TbaCategoriaCliente;
import com.sicon.tba.service.TbaCajaService;
import com.sicon.tba.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Clase que maneja las peticiones de caja
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaCajaHandler {

    private final TbaCajaService tbaCajaService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> findCajaByIdSucusal(ServerRequest request) {
        int sucursalId = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().body(tbaCajaService.findCajaByIdSucusal(sucursalId), Caja.class)
                .onErrorResume(error -> Mono.error(new CustomException(HttpStatus.BAD_REQUEST, error.getMessage())));
    }

    public Mono<ServerResponse> findCajaById(ServerRequest request) {
        int cajaId = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().body(tbaCajaService.findById(cajaId), Caja.class)
                .onErrorResume(error -> Mono.error(new CustomException(HttpStatus.BAD_REQUEST, error.getMessage())));
    }

    public Mono<ServerResponse> getRequestBySearchParams(ServerRequest request) {
        String branchIdParam = request.queryParam("branchId").orElse(null);
        Integer branchId = (branchIdParam == null || branchIdParam.isEmpty()) ? null : Integer.valueOf(branchIdParam);
        String nameParam = request.queryParam("name").orElse(null);
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        return ServerResponse.ok().body(tbaCajaService.getRequestBySearchParams(branchId, nameParam, page, limit, sortDirection, sortField), Caja.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<Caja> cajaMono = request.bodyToMono(Caja.class).doOnNext(objectValidator::validate).log();
        return cajaMono.flatMap(caja ->
                        ServerResponse.ok().body(tbaCajaService.createCaja(caja), Caja.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<Caja> cajaMono = request.bodyToMono(Caja.class).doOnNext(objectValidator::validate).log();
        return cajaMono.flatMap(caja ->
                        ServerResponse.ok().body(tbaCajaService.updateCaja(id, caja), Caja.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

}
