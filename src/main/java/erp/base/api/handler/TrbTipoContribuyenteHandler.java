package erp.base.api.handler;

import erp.base.api.dto.TrbTipoContribuyenteDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TrbTipoContribuyente;
import erp.base.api.service.TrbTipoContribuyenteService;
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
public class TrbTipoContribuyenteHandler {

    private final TrbTipoContribuyenteService trbTipoContribuyenteService;

    private final ObjectValidator objectValidator;


    /**
     * Método que crea un tipo de contribuyente
     * @param serverRequest petición
     * @return tipo de contribuyente creado
     */
    public Mono<ServerResponse> createContributorType(ServerRequest serverRequest){
        Mono<TrbTipoContribuyenteDto> trbTipoContribuyenteDtoMono = serverRequest.bodyToMono(TrbTipoContribuyenteDto.class).doOnNext(objectValidator::validate).log();
        return trbTipoContribuyenteDtoMono.flatMap(trbTipoContribuyenteDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbTipoContribuyenteService.createContributorType(trbTipoContribuyenteDto), TrbTipoContribuyente.class))
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
    public Mono<ServerResponse> updateContributorType(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        Mono<TrbTipoContribuyenteDto> trbTipoContribuyenteDtoMono = serverRequest.bodyToMono(TrbTipoContribuyenteDto.class).doOnNext(objectValidator::validate).log();
        return trbTipoContribuyenteDtoMono.flatMap(trbTipoContribuyenteDto ->  ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbTipoContribuyenteService.updateContributorType(id, trbTipoContribuyenteDto), TrbTipoContribuyente.class))
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
    public Mono<ServerResponse> getContributorTypeById(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbTipoContribuyenteService.getContributorTypeById(id), TrbTipoContribuyente.class)
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
    public Mono<ServerResponse> getAllContributorTypes(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(trbTipoContribuyenteService.getAllContributorTypes(), TrbTipoContribuyente.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
