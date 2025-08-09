package com.sicon.tba.handler;

import com.sicon.tba.constants.Constants;
import com.sicon.tba.dto.TbaBancoDto;
import com.sicon.tba.dto.TbaTipoCuentaBancariaDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaBanco;
import com.sicon.tba.service.TbaBancoService;
import com.sicon.tba.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Log4j2
public class TbaBancoHandler {

    private final TbaBancoService tbaBancoService;
    private final ObjectValidator objectValidator;

    /**
     * Método que crea un Banco
     *
     * @param request petición
     * @return Banco creado
     */

    public Mono<ServerResponse> createBanck(ServerRequest request) {
        Mono<TbaBancoDto> tbaBancoDtoMono = request.bodyToMono(TbaBancoDto.class).doOnNext(objectValidator::validate).log();
        return tbaBancoDtoMono.flatMap(tbaBancoDto ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaBancoService.createBank(tbaBancoDto), TbaBanco.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualizar un Banco
     *
     * @param request petición
     * @return Banco actualizado
     */

    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaBancoDto> tbaBancoDtoMono = request.bodyToMono(TbaBancoDto.class).doOnNext(objectValidator::validate).log();
        return tbaBancoDtoMono.flatMap(tbaBancoDto ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaBancoService.updateBank(id, tbaBancoDto).onErrorMap(e -> {
                            log.error(e.getMessage());
                            return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                        }), TbaBanco.class

                ));
    }

    /**
     * Método que obtiene un banco por ID
     *
     * @param request petición
     * @return Objeto del Banco
     */

    public Mono<ServerResponse> getById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaBanco> bankMono = tbaBancoService.getById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bankMono, TbaBanco.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene un banco por parametros
     *
     * @param request peticion
     * @return lista de Bancos
     */

    public Mono<ServerResponse> getBanksBySearchParams(ServerRequest request) {
        String nameBank = request.queryParam("bankName").orElse(null);
        String numBank = request.queryParam("bankAccountNumber").orElse(null);
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        return ServerResponse.ok().body(tbaBancoService.getRequestBySearchParams(nameBank, numBank, page, limit, sortDirection, sortField), TbaBanco.class)
                .onErrorMap(e -> {
                    log.error(Constants.GENERIC_ERROR_MESSAGE_WITH_CAUSE, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e);
                    return new CustomException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE);
                });
    }

    public Mono<ServerResponse> getBankList(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaBancoService.getBankList(), TbaBanco.class)
                .onErrorMap(e -> {
                    log.error(Constants.GENERIC_ERROR_MESSAGE_WITH_CAUSE, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e);
                    return new CustomException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE);
                });
    }

    public Mono<ServerResponse> getBankListByIdSucursal(ServerRequest request) {
        Integer idSucursal = Integer.valueOf(Objects.requireNonNull(request.queryParam("branchId").orElse(null)));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaBancoService.getBankListByIdSucursal(idSucursal), TbaBanco.class)
                .onErrorMap(e -> {
                    log.error(Constants.GENERIC_ERROR_MESSAGE_WITH_CAUSE, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e);
                    return new CustomException(HttpStatus.BAD_REQUEST, Constants.GENERIC_ERROR_MESSAGE);
                });
    }

    public Mono<ServerResponse> getBankListIsBank(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaBancoService.getBankListIsBank(), TbaBanco.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getLastCheckBank(ServerRequest request) {
        Integer bankId = Integer.valueOf(request.queryParam("bankId").orElse("1"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaBancoService.getLastCheckBank(bankId), Integer.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

}
