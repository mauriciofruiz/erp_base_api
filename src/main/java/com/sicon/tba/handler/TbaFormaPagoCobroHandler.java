package com.sicon.tba.handler;

import com.sicon.tba.dto.TbaFormaPagoCobroDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaFormaPagoCobro;
import com.sicon.tba.service.TbaFormaPagoCobroService;
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
public class TbaFormaPagoCobroHandler {

    private final TbaFormaPagoCobroService tbaFormaPagoCobroService;
    private final ObjectValidator objectValidator;


    public Mono<ServerResponse> createPaymentCollectionMethod(ServerRequest serverRequest) {
        Mono<TbaFormaPagoCobroDto> tbaFormaPagoCobroDtoMono = serverRequest.bodyToMono(TbaFormaPagoCobroDto.class).doOnNext(objectValidator::validate).log();
        return tbaFormaPagoCobroDtoMono.flatMap(tbaFormaPagoCobroDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaFormaPagoCobroService.createPaymentCollectionMethod(tbaFormaPagoCobroDto), TbaFormaPagoCobro.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> updatePaymentCollectionMethod(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<TbaFormaPagoCobroDto> tbaFormaPagoCobroDtoMono = serverRequest.bodyToMono(TbaFormaPagoCobroDto.class).doOnNext(objectValidator::validate).log();
        return tbaFormaPagoCobroDtoMono.flatMap(tbaFormaPagoCobroDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaFormaPagoCobroService.updatePaymentCollectionMethod(id, tbaFormaPagoCobroDto), TbaFormaPagoCobro.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getPaymentCollectionMethod(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaFormaPagoCobroService.getPaymentCollectionMethod(id), TbaFormaPagoCobro.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAllFormaPagoCobro(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaFormaPagoCobroService.getAllPaymentCollectionMethod(), TbaFormaPagoCobro.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getRequestBySearchParams(ServerRequest request) {
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        if (request.queryParams().isEmpty()) {
            return getAllFormaPagoCobro(request);
        }

        return ServerResponse.ok().body(tbaFormaPagoCobroService.getRequestBySearchParams(page, limit, sortDirection, sortField), TbaFormaPagoCobro.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
