package erp.base.api.handler;

import erp.base.api.exception.CustomException;
import erp.base.api.models.Caja;
import erp.base.api.models.TbaBanco;
import erp.base.api.models.TbaBancoCatalogo;
import erp.base.api.service.TbaBancoCatalogoService;
import erp.base.api.validation.ObjectValidator;
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
public class TbaBancoCatalogoHandler {

    private final TbaBancoCatalogoService tbaBancoCatalogoService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> getAllBankCatalog(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaBancoCatalogoService.getBankCatalog(), TbaBancoCatalogo.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getCatalogBanksBySearchParams(ServerRequest request) {
        String nameCatalogBank = request.queryParam("bankCatalogName").orElse(null);
        String codeCatalog = request.queryParam("bankCatalogCode").orElse(null);
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        if(request.queryParams().isEmpty())
            return getAllBankCatalog(request);

        return ServerResponse.ok().body(tbaBancoCatalogoService.getRequestBySearchParams(nameCatalogBank, codeCatalog, page, limit, sortDirection, sortField), TbaBancoCatalogo.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<TbaBancoCatalogo> bancoCatalogoMono = request.bodyToMono(TbaBancoCatalogo.class).doOnNext(objectValidator::validate).log();
        return bancoCatalogoMono.flatMap(bancoCatalogo ->
                        ServerResponse.ok().body(tbaBancoCatalogoService.createCatalogBank(bancoCatalogo), TbaBancoCatalogo.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaBancoCatalogo> bancoCatalogoMono = request.bodyToMono(TbaBancoCatalogo.class).doOnNext(objectValidator::validate).log();
        return bancoCatalogoMono.flatMap(bancoCatalogo ->
                        ServerResponse.ok().body(tbaBancoCatalogoService.updateCatalogBank(id, bancoCatalogo), TbaBancoCatalogo.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
