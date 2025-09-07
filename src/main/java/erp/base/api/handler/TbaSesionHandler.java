package erp.base.api.handler;

import erp.base.api.dto.SesionDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Sesion;
import erp.base.api.models.Sucursal;
import erp.base.api.models.TbaRol;
import erp.base.api.service.SesionService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Log4j2
public class TbaSesionHandler {
    private final SesionService tbaSesionService;
    private final ObjectValidator validator;
    private final SesionService sesionService;

    public Mono<ServerResponse> updateStatusSesion(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return tbaSesionService.updateStatusSesion(id)
                .flatMap(role -> ServerResponse.ok().body(Mono.just(role), Sesion.class));
    }

    public Mono<ServerResponse> updateAllStatusSesion(ServerRequest request) {
        return request.bodyToFlux(SesionDto.class)
                .collectList()
                .flatMap(sesionList ->
                        sesionService.updateAllSesion(sesionList)
                                .collectList()
                                .flatMap(updatedSesions -> ServerResponse.ok().bodyValue(updatedSesions))
                                .onErrorResume(CustomException.class, e ->
                                        ServerResponse.status(e.getStatus()).bodyValue(e.getMessage()))
                );
    }

    public Mono<ServerResponse> getAllSesiones() {
        return ServerResponse.ok().body(tbaSesionService.getAllSessions(), Sesion.class);
    }

    public Mono<ServerResponse> getBySearchParams(ServerRequest request) {
        Integer userId = request.queryParam("userId").map(Integer::valueOf).orElse(null);
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        if (request.queryParams().isEmpty()) {
            return getAllSesiones();
        }

        return ServerResponse.ok().body(tbaSesionService.getRequestBySearchParams(userId, page, limit, sortDirection, sortField), TbaRol.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
