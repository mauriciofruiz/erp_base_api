package erp.base.api.handler;

import erp.base.api.dto.TbaCargoDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaCargo;
import erp.base.api.service.TbaCargoService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que maneja las peticiones de cargo
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaCargoHandler {

    private final TbaCargoService tbaCargoService;
    private final ObjectValidator objectValidator;

    /**
     * Método que crea un cargo
     *
     * @param request petición
     * @return cargo creado
     */
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<TbaCargoDto> tbaCargoDtoMono = request.bodyToMono(TbaCargoDto.class).doOnNext(objectValidator::validate).log();
        return tbaCargoDtoMono.flatMap(tbaCargoDto ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaCargoService.createServiceCharge(tbaCargoDto), TbaCargo.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza un cargo
     *
     * @param request petición
     * @return cargo actualizado
     */
    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaCargoDto> tbaCargoDtoMono = request.bodyToMono(TbaCargoDto.class).doOnNext(objectValidator::validate).log();
        return tbaCargoDtoMono.flatMap(tbaCargoDto ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaCargoService.updateServiceCharge(id, tbaCargoDto), TbaCargo.class)
                )
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene un cargo por id
     *
     * @param request petición
     * @return cargo
     */
    public Mono<ServerResponse> getById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaCargo> positionMono = tbaCargoService.getServiceChargeById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(positionMono, TbaCargo.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene todos los cargos
     *
     * @param request petición
     * @return lista de cargos
     */
    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<TbaCargo> positionFlux = tbaCargoService.getAllServiceCharge();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(positionFlux, TbaCargo.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
