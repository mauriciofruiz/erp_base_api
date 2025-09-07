package erp.base.api.handler;

import erp.base.api.dto.TrbIdentificacionFiscalDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TrbIdentificacionFiscal;
import erp.base.api.service.TrbIdentificacionFiscalService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Clase que maneja las peticiones de identificación fiscal.
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TrbIdentificacionFiscalHandler {

    private final TrbIdentificacionFiscalService trbIdentificacionFiscalService;

    private final ObjectValidator objectValidator;

    /**
     * Método que crea una identificación fiscal.
     * @param request petición
     * @return identificación fiscal creada
     */
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<TrbIdentificacionFiscalDto> trbIdentificacionFiscalDtoMono = request.bodyToMono(TrbIdentificacionFiscalDto.class).doOnNext(objectValidator::validate).log();
        return trbIdentificacionFiscalDtoMono.flatMap(trbIdentificacionFiscalDto ->
                ServerResponse.ok().body(trbIdentificacionFiscalService.createTaxIdentification(trbIdentificacionFiscalDto), TrbIdentificacionFiscal.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza una identificación fiscal.
     * @param request petición
     * @return identificación fiscal actualizada
     */
    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TrbIdentificacionFiscalDto> trbIdentificacionFiscalDtoMono = request.bodyToMono(TrbIdentificacionFiscalDto.class).doOnNext(objectValidator::validate).log();
        return trbIdentificacionFiscalDtoMono.flatMap(trbIdentificacionFiscalDto ->
                ServerResponse.ok().body(trbIdentificacionFiscalService.updateTaxIdentification(id, trbIdentificacionFiscalDto), TrbIdentificacionFiscal.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene una identificación fiscal por id.
     * @param request petición
     * @return identificación fiscal
     */
    public Mono<ServerResponse> getById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().body(trbIdentificacionFiscalService.getTaxIdentificationById(id), TrbIdentificacionFiscal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene todas las identificaciones fiscales.
     * @param request petición
     * @return  lista de identificaciones fiscales
     */
    public Mono<ServerResponse> getAll(ServerRequest request){
        return ServerResponse.ok().body(trbIdentificacionFiscalService.getAllTaxIdentification(), TrbIdentificacionFiscal.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
