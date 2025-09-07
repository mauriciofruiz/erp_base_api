package erp.base.api.handler;

import erp.base.api.dto.ExcepcionesClienteDescDto;
import erp.base.api.exception.CustomException;
import erp.base.api.service.ExcepcionesClienteDescuentoService;
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
public class ExcepcionesClienteDescuentoHandler {

    private final ExcepcionesClienteDescuentoService excepcionesClienteDescuentoService;

    public Mono<ServerResponse> delete(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return excepcionesClienteDescuentoService.delete(id).then(Mono.defer(() -> ServerResponse.noContent().build()));
    }

    /**
     * Método que devuelve la consulta de excepciones de cliente de descuento con los parámetros de búsqueda y paginación
     *
     * @param request petición
     * @return lista de excepciones de cliente de descuento
     */
    public Mono<ServerResponse> getByParams(ServerRequest request) {
        Integer clientId = request.queryParam("clientId").map(Integer::valueOf).orElse(null);
        if (clientId == null) return ServerResponse.badRequest().bodyValue("El id del cliente es requerido");
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);
        return ServerResponse.ok().body(excepcionesClienteDescuentoService.getRequestsBySearchParams(clientId, page, limit, sortDirection, sortField), ExcepcionesClienteDescDto.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
