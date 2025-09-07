package erp.base.api.handler;

import erp.base.api.models.TbaProyecto;
import erp.base.api.service.TbaProyectoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Log4j2
public class TbaProyectoHandler {

    private final TbaProyectoService tbaProyectoService;

    public Mono<ServerResponse> getAllProjectsActive(ServerRequest request) {
        return ServerResponse.ok().body(tbaProyectoService.findAllProjectsActive(), TbaProyecto.class);
    }


}
