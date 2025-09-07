package erp.base.api.handler;

import erp.base.api.dto.TrbTipoIdentificacionFiscalDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TrbTipoIdentificacionFiscal;
import erp.base.api.service.TrbTipoIdentificacionFiscalService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Clase que maneja las peticiones de tipos de contribuyente
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TrbTipoIdentificacionFiscalHandler {

    private final TrbTipoIdentificacionFiscalService trbTipoIdentificacionFiscalService;

    private final ObjectValidator objectValidator;


    /**
     * Método que crea un tipo de contribuyente
     * @param serverRequest petición
     * @return tipo de contribuyente creado
     */
    public Mono<ServerResponse> createFiscalIdentificationType(ServerRequest serverRequest){
        Mono<TrbTipoIdentificacionFiscalDto> trbTipoIdentificacionFiscalDtoMono = serverRequest.bodyToMono(TrbTipoIdentificacionFiscalDto.class).doOnNext(objectValidator::validate).log();
        return trbTipoIdentificacionFiscalDtoMono.flatMap(trbTipoIdentificacionFiscalDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbTipoIdentificacionFiscalService.createFiscalIdentificationType(trbTipoIdentificacionFiscalDto), TrbTipoIdentificacionFiscal.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza un tipo de contribuyente
     * @param serverRequest petición
     * @return tipo de contribuyente actualizado
     */
    public Mono<ServerResponse> updateFiscalIdentificationType(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        Mono<TrbTipoIdentificacionFiscalDto> trbTipoIdentificacionFiscalDtoMono = serverRequest.bodyToMono(TrbTipoIdentificacionFiscalDto.class).doOnNext(objectValidator::validate).log();
        return trbTipoIdentificacionFiscalDtoMono.flatMap(trbTipoIdentificacionFiscalDto ->  ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbTipoIdentificacionFiscalService.updateFiscalIdentificationType(id, trbTipoIdentificacionFiscalDto), TrbTipoIdentificacionFiscal.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene un tipo de contribuyente por id
     * @param serverRequest petición
     * @return tipo de contribuyente
     */
    public Mono<ServerResponse> getFiscalIdentificationTypeById(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbTipoIdentificacionFiscalService.getFiscalIdentificationType(id), TrbTipoIdentificacionFiscal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene todos los tipos de contribuyente
     * @param serverRequest petición
     * @return tipos de contribuyente
     */
    public Mono<ServerResponse> getAllFiscalIdentificationType(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbTipoIdentificacionFiscalService.getAllFiscalIdentificationType(), TrbTipoIdentificacionFiscal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
