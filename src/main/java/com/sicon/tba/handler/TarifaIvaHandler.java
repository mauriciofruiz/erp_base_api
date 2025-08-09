package com.sicon.tba.handler;

import com.sicon.tba.dto.TarifaIvaDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TarifaIva;
import com.sicon.tba.service.TarifaIvaService;
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
public class TarifaIvaHandler {
    private final TarifaIvaService tarifaIvaService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Mono<TarifaIvaDto> tarifaIvaDtoMono = serverRequest.bodyToMono(TarifaIvaDto.class).doOnNext(objectValidator::validate).log();
        return tarifaIvaDtoMono.flatMap(tarifaIvaDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tarifaIvaService.create(tarifaIvaDto), TarifaIva.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<TarifaIvaDto> tarifaIvaDtoMono = serverRequest.bodyToMono(TarifaIvaDto.class).doOnNext(objectValidator::validate).log();
        return tarifaIvaDtoMono.flatMap(tarifaIvaDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tarifaIvaService.update(id, tarifaIvaDto), TarifaIva.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tarifaIvaService.getById(id), TarifaIva.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAllActives(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tarifaIvaService.getAllActive(), TarifaIva.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
