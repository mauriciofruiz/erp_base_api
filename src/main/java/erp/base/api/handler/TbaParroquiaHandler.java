package erp.base.api.handler;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaParroquiaDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaParroquia;
import erp.base.api.service.TbaParroquiaService;
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
 * Clase que maneja las peticiones de parroquia.
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaParroquiaHandler {

    private final TbaParroquiaService tbaParroquiaService;
    private final ObjectValidator objectValidator;

    /**
     * Método que crea una parroquia.
     *
     * @param request petición
     * @return parroquia creada
     */
    public Mono<ServerResponse> createParish(ServerRequest request) {
        Mono<TbaParroquiaDto> tbaParroquiaDtoMono = request.bodyToMono(TbaParroquiaDto.class).doOnNext(objectValidator::validate).log();
        return tbaParroquiaDtoMono.flatMap(tbaParroquiaDto -> ServerResponse.ok().body(tbaParroquiaService.createParish(tbaParroquiaDto), TbaParroquia.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza una parroquia.
     *
     * @param request petición
     * @return parroquia actualizada
     */
    public Mono<ServerResponse> updateParish(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        Mono<TbaParroquiaDto> tbaParroquiaDtoMono = request.bodyToMono(TbaParroquiaDto.class).doOnNext(objectValidator::validate).log();
        return tbaParroquiaDtoMono.flatMap(tbaParroquiaDto -> ServerResponse.ok().body(tbaParroquiaService.updateParish(id, tbaParroquiaDto), TbaParroquia.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene una parroquia por id.
     *
     * @param request petición
     * @return parroquia
     */
    public Mono<ServerResponse> getParishById(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().body(tbaParroquiaService.getParishById(id), TbaParroquia.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene todas las parroquias.
     * @param request petición
     * @return lista de parroquias
     */
    public Mono<ServerResponse> getAllParishes(ServerRequest request){
        return ServerResponse.ok().body(tbaParroquiaService.getAllParishes(), TbaParroquia.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /*
     * Método que obtiene la parroquia por nombre
     * @param request petición
     * @return lista de parroquias
     */
    public Mono<ServerResponse> getParish(ServerRequest request) {
        String paramSearch = request.queryParam("param").orElse(null);
        try {
            if (paramSearch != null) {
                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaParroquiaService.getParish(paramSearch), TbaParroquiaDto.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
            } else {
                return getAllParishes(request);
            }
        } catch (Exception e) {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER));
        }
    }


    public Mono<ServerResponse> getParishByCityId(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().body(tbaParroquiaService.getParishByCityId(id), TbaParroquia.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

}
