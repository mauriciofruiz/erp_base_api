package erp.base.api.handler;

import erp.base.api.dto.TrbRetencionIvaDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Retencion;
import erp.base.api.service.TrbRetencionIvaService;
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
public class TrbRetencionIvaHandler {
    private final TrbRetencionIvaService trbRetencionIvaService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> createRetentionIva(ServerRequest serverRequest){
        Mono<TrbRetencionIvaDto> trbRetencionIvaDtoMono = serverRequest.bodyToMono(TrbRetencionIvaDto.class).doOnNext(objectValidator::validate).log();
        return trbRetencionIvaDtoMono.flatMap(trbRetencionIvaDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionIvaService.createRetentionIva(trbRetencionIvaDto), Retencion.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
    public Mono<ServerResponse> updateRetentionIva(ServerRequest serverRequest){
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<TrbRetencionIvaDto> trbRetencionIvaDtoMono = serverRequest.bodyToMono(TrbRetencionIvaDto.class).doOnNext(objectValidator::validate).log();
        return trbRetencionIvaDtoMono.flatMap(trbRetencionIvaDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionIvaService.updateRetentionIva(id, trbRetencionIvaDto), Retencion.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
    public Mono<ServerResponse> getRetentionIva(ServerRequest serverRequest){
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionIvaService.getRetentionIva(id), Retencion.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
    public Mono<ServerResponse> getAllRetentionIva(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionIvaService.getAllRetentionIva(), Retencion.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    public Mono<ServerResponse> getAllRetentionIvaCliente(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbRetencionIvaService.getAllRetentionIvaCliente(), Retencion.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
