package erp.base.api.handler;

import erp.base.api.models.TbaTipoVehiculo;
import erp.base.api.service.TbaTipoVehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TbaTipoVehiculoHandler {

    private final TbaTipoVehiculoService tbaTipoVehiculoService;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(TbaTipoVehiculo.class)
                .flatMap(tbaTipoVehiculoService::createVehicleType)
                .flatMap(tbaTipoVehiculo -> ServerResponse.ok().bodyValue(tbaTipoVehiculo));
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(tbaTipoVehiculoService.findAll(), TbaTipoVehiculo.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return tbaTipoVehiculoService.findById(id)
                .flatMap(tbaTipoVehiculo -> ServerResponse.ok().bodyValue(tbaTipoVehiculo));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return tbaTipoVehiculoService.delete(id)
                .then(ServerResponse.noContent().build());
    }


}
