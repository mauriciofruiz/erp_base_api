package com.sicon.tba.controller;

import com.sicon.tba.Preference;
import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.BodegaDto;
import com.sicon.tba.dto.response.EstadoCuentoPagarDto;
import com.sicon.tba.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

@Service
public class ContabilidadController {

    private WebClient webClient;

    // Constructor to initialize WebClient with a token
    @Autowired
    public ContabilidadController(Preference preference) {
        this.webClient = WebClient.builder()
                .baseUrl(preference.getContabilidadService())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<EstadoCuentoPagarDto> getEstadoCuentaPagar(Integer idProveedor, HttpHeaders headers) {
        return webClient
                .get()
                .uri("/factura-cuota/proveedor/" + idProveedor)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorMessage -> Mono.error(new CustomException((HttpStatus) clientResponse.statusCode(), errorMessage)))
                )
                .bodyToMono(EstadoCuentoPagarDto.class)
                .onErrorResume(e -> Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ERROR_GETTING_DATA_CONTABILIDAD + " - " + ErrorMessage.PROVIDER_NOT_FOUND + idProveedor)));
    }

}
 