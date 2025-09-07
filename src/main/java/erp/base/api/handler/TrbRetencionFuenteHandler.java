package erp.base.api.handler;

import erp.base.api.dto.TrbRetencionFuenteDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Retencion;
import erp.base.api.service.TrbRetencionFuenteService;
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
public class TrbRetencionFuenteHandler {
    private final TrbRetencionFuenteService trbRetencionFuenteService;
    private final ObjectValidator objectValidator;

    /**
     * Método para crear una retención en la fuente
     *
     * @param serverRequest solicitud del cliente
     * @return respuesta del servidor
     */
    public Mono<ServerResponse> createRetentionSource(ServerRequest serverRequest) {
        Mono<TrbRetencionFuenteDto> trbRetencionFuenteMono = serverRequest.bodyToMono(TrbRetencionFuenteDto.class).doOnNext(objectValidator::validate).log();
        return trbRetencionFuenteMono.flatMap(trbRetencionFuente -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionFuenteService.createRetentionSource(trbRetencionFuente), Retencion.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método para actualizar una retención en la fuente
     *
     * @param serverRequest solicitud del cliente
     * @return respuesta del servidor
     */
    public Mono<ServerResponse> updateRetentionSource(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<TrbRetencionFuenteDto> trbRetencionFuenteMono = serverRequest.bodyToMono(TrbRetencionFuenteDto.class).doOnNext(objectValidator::validate).log();
        return trbRetencionFuenteMono.flatMap(trbRetencionFuente -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionFuenteService.updateRetentionSource(id, trbRetencionFuente), Retencion.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método para obtener una retención en la fuente por ID
     *
     * @param serverRequest solicitud del cliente
     * @return respuesta del servidor
     */
    public Mono<ServerResponse> getRetentionSourceById(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionFuenteService.getRetentionSourceById(id), Retencion.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método para obtener todas las retenciones en la fuente
     *
     * @param serverRequest solicitud del cliente
     * @return respuesta del servidor
     */
    public Mono<ServerResponse> getAllRetentionSource(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionFuenteService.getAllRetentionSource(), Retencion.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAllRetentionSourceClient(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionFuenteService.getAllRetentionSourceClient(), Retencion.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
