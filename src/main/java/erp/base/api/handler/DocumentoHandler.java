package erp.base.api.handler;

import erp.base.api.dto.DocumentoDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Sucursal;
import erp.base.api.service.DocumentoService;
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
public class DocumentoHandler {

    private final DocumentoService documentoService;

    public Mono<ServerResponse> getRequestBySearchParams(ServerRequest request) {
        String documentNumber = request.queryParam("documentNumber").orElse(null);
        String documentType = request.queryParam("documentType").orElse(null);
        String name = request.queryParam("name").orElse(null);
        String identification = request.queryParam("identification").orElse(null);
        String accesKey = request.queryParam("accesKey").orElse(null);
        Boolean isAuthorized = Boolean.valueOf(request.queryParam("isAuthorized").orElse("false"));
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        return ServerResponse.ok().body(documentoService.getRequestBySearchParams(documentNumber, documentType, name, identification, accesKey, isAuthorized, page, limit, sortDirection, sortField), DocumentoDto.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
