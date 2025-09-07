package erp.base.api.handler;

import erp.base.api.dto.TbaSucursalDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Sucursal;
import erp.base.api.models.TbaCategoriaCliente;
import erp.base.api.service.TbaCategoriaClienteService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Log4j2
public class TbaCategoriaClienteHandler {

    private final TbaCategoriaClienteService tbaCategoriaClienteService;
    private final ObjectValidator objectValidator;


    public Mono<ServerResponse> getAllCategoryClient(ServerRequest request) {
        return ServerResponse.ok().body(tbaCategoriaClienteService.getAllCategoryClient(), TbaCategoriaCliente.class);
    }

    public Mono<ServerResponse> getRequestBySearchParams(ServerRequest request) {
        String nameParam = request.queryParam("name").orElse(null);
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        if(request.queryParams().isEmpty()){
            return getAllCategoryClient(request);
        }

        return ServerResponse.ok().body(tbaCategoriaClienteService.getRequestBySearchParams(nameParam, page, limit, sortDirection, sortField), TbaCategoriaCliente.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<TbaCategoriaCliente> tbaCategoriaClienteMono = request.bodyToMono(TbaCategoriaCliente.class).doOnNext(objectValidator::validate).log();
        return tbaCategoriaClienteMono.flatMap(tbaCategoriaCliente ->
                        ServerResponse.ok().body(tbaCategoriaClienteService.createClientCategory(tbaCategoriaCliente), TbaCategoriaCliente.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaCategoriaCliente> tbaCategoriaClienteMono = request.bodyToMono(TbaCategoriaCliente.class).doOnNext(objectValidator::validate).log();
        return tbaCategoriaClienteMono.flatMap(tbaCategoriaCliente ->
                        ServerResponse.ok().body(tbaCategoriaClienteService.updateClientCategory(id, tbaCategoriaCliente), TbaCategoriaCliente.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
