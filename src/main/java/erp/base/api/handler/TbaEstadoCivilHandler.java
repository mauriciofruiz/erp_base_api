package erp.base.api.handler;

import erp.base.api.dto.TbaEstadoCivilDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaEstadoCivil;
import erp.base.api.service.TbaEstadoCivilService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Clase que maneja las peticiones de estado civil.
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaEstadoCivilHandler {

    public final TbaEstadoCivilService tbaEstadoCivilService;

    public final ObjectValidator objectValidator;

    /**
     * Método que crea un estado civil.
     * @param request petición
     * @return estado civil creado
     */
    public Mono<ServerResponse> createMaritalStatus(ServerRequest request){
        Mono<TbaEstadoCivilDto> tbaEstadoCivilDtoMono = request.bodyToMono(TbaEstadoCivilDto.class).doOnNext(objectValidator::validate).log();
        return tbaEstadoCivilDtoMono.flatMap(tbaEstadoCivilDto -> ServerResponse.ok().body(tbaEstadoCivilService.createMaritalStatus(tbaEstadoCivilDto), TbaEstadoCivil.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza un estado civil.
     * @param request petición
     * @return estado civil actualizado
     */
    public Mono<ServerResponse> updateMaritalStatus(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaEstadoCivilDto> tbaEstadoCivilDtoMono = request.bodyToMono(TbaEstadoCivilDto.class).doOnNext(objectValidator::validate).log();
        return tbaEstadoCivilDtoMono.flatMap(tbaEstadoCivilDto -> ServerResponse.ok().body(tbaEstadoCivilService.updateMaritalStatus(id, tbaEstadoCivilDto), TbaEstadoCivil.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene un estado civil por id.
     * @param request petición
     * @return estado civil
     */
    public Mono<ServerResponse> getMaritalStatusById(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().body(tbaEstadoCivilService.getMaritalStatusById(id), TbaEstadoCivil.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    /**
     * Método que obtiene todos los estados civiles.
     * @param request petición
     * @return lista de estados civiles
     */
    public Mono<ServerResponse> getAllMaritalStatus(ServerRequest request){
        return ServerResponse.ok().body(tbaEstadoCivilService.getAllMaritalStatus(), TbaEstadoCivil.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
