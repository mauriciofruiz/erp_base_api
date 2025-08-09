package com.sicon.tba.controller;

import com.sicon.tba.Preference;
import com.sicon.tba.dto.BodegaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

@Service
public class InventarioController {

    private WebClient webClient;

    // Constructor to initialize WebClient with a token
    @Autowired
    public InventarioController(Preference preference) {
        this.webClient = WebClient.builder()
                .baseUrl(preference.getInventarioService())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<Void> createWareHouse(List<BodegaDto> bodegaDtoList, Consumer<HttpHeaders> headers) {
        return webClient
                .post()
                .uri("/bodega")
                .headers(headers)
                .bodyValue(bodegaDtoList)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> updateWareHouse(List<BodegaDto> bodegaDtoList, Consumer<HttpHeaders> headers) {
        return webClient
                .put()
                .uri("/bodega")
                .headers(headers)
                .bodyValue(bodegaDtoList)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
 