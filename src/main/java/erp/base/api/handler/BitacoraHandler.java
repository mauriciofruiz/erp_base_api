package erp.base.api.handler;

import erp.base.api.dto.BitacoraDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Bitacora;
import erp.base.api.service.BitacoraService;
import erp.base.api.utils.Paginacion;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static erp.base.api.utils.Utils.getClientHostname;
import static erp.base.api.utils.Utils.getClientIp;

@Component
@RequiredArgsConstructor
@Log4j2
public class BitacoraHandler {

    private final BitacoraService bitacoraService;

    private final ObjectValidator objectValidator;

    /**
     * Save Bitacora
     *
     * @param request ServerRequest
     * @return ServerResponse
     */
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Bitacora> bitacoraMono = request.bodyToMono(Bitacora.class)
                .doOnNext(objectValidator::validate);
        String clientIp = getClientIp(request);
        String clientHostname = getClientHostname(request);
        return bitacoraMono.flatMap(bitacora ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(bitacoraService.saveTransactionLog(bitacora, clientIp, clientHostname), Bitacora.class)).onErrorMap(e -> {
            log.error(e.getMessage());
            return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        });
    }

    public Mono<ServerResponse> findAllByIdAndTable(ServerRequest request) {
        Integer id = Integer.parseInt(request.queryParam("id").orElse("1"));
        String table = request.queryParam("table").orElse("Producto");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bitacoraService.findAllByIdAndTable(id, table), BitacoraDto.class).onErrorMap(e -> {
            log.error(e.getMessage());
            return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        });
    }

    public Mono<ServerResponse> findTransactionLogByClient(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse("desc");
        String sortField = request.queryParam("sortField").orElse("date");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bitacoraService.getTransactionLogByClient(id, page, limit, sortDirection, sortField), Paginacion.class).onErrorMap(e -> {
            log.error(e.getMessage());
            return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        });
    }


}
