package com.sicon.tba.handler;


import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaAdquiriente;
import com.sicon.tba.models.TbaTarjeta;
import com.sicon.tba.service.AdquirienteService;
import com.sicon.tba.service.TarjetaService;
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
public class AdquirienteHandler {

    private final AdquirienteService adquirienteService;

    public Mono<ServerResponse> getAllByActiveEstado(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(adquirienteService.getAllByActiveEstado(), TbaAdquiriente.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

}