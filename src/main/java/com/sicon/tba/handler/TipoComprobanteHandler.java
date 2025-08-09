package com.sicon.tba.handler;

import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TipoComprobante;
import com.sicon.tba.service.TipoComprobanteService;
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
public class TipoComprobanteHandler {

    private final TipoComprobanteService tipoComprobanteService;



    /**
     * Método que devuelve todos los tipos de comprobante
     * @param request Objeto con los datos de la petición
     * @return Objeto con una lista de los tipos de comprobante
     */
    public Mono<ServerResponse> getAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tipoComprobanteService.getAllTipoComprobante(), TipoComprobante.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }



}
