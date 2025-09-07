package erp.base.api.handler;

import erp.base.api.dto.TbaGeneroDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaGenero;
import erp.base.api.service.TbaGeneroService;
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
 * Clase que maneja las peticiones de género
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaGeneroHandler {

    private final TbaGeneroService tbaGeneroService;

    private final ObjectValidator objectValidator;

    /**
     * Método que crea un género
     * @param request petición
     * @return  género creado
     */
    public Mono<ServerResponse> create(ServerRequest request){
        Mono<TbaGeneroDto> tbaGeneroDtoMono = request.bodyToMono(TbaGeneroDto.class).doOnNext(objectValidator::validate).log();
        return tbaGeneroDtoMono.flatMap(tbaGeneroDto ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaGeneroService.createGender(tbaGeneroDto), TbaGenero.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza un género
     * @param request petición
     * @return género actualizado
     */
    public Mono<ServerResponse> update(ServerRequest request){
        Integer id= Integer.parseInt(request.pathVariable("id"));
        Mono<TbaGeneroDto> tbaGeneroDtoMono = request.bodyToMono(TbaGeneroDto.class).doOnNext(objectValidator::validate).log();
        return tbaGeneroDtoMono.flatMap(tbaGeneroDto ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaGeneroService.updateGender(id,tbaGeneroDto), TbaGenero.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene un género por id
     * @param request petición
     * @return género
     */
    public Mono<ServerResponse> getById(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaGenero> tbaGeneroMono = tbaGeneroService.getGenderById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaGeneroMono, TbaGenero.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene todos los géneros
     * @param request petición
     * @return géneros
     */
    public Mono<ServerResponse> getAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaGeneroService.getAllGenders(), TbaGenero.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
