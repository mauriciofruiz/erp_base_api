package erp.base.api.handler;

import erp.base.api.dto.TbaPaisDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaPais;
import erp.base.api.service.TbaPaisService;
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
 * Clase que maneja las peticiones de países
 */
@Component
@RequiredArgsConstructor
 @Log4j2
public class TbaPaisHandler {

    public final TbaPaisService tbaPaisService;

    public final ObjectValidator objectValidator;

    public Mono<ServerResponse> createCountry(ServerRequest request){
        Mono<TbaPaisDto> tbaPaisDtoMono = request.bodyToMono(TbaPaisDto.class).doOnNext(objectValidator::validate).log();
        return tbaPaisDtoMono.flatMap(tbaPaisDto-> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaPaisService.createCountry(tbaPaisDto), TbaPais.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> updateCountry(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaPaisDto> tbaPaisDtoMono = request.bodyToMono(TbaPaisDto.class).doOnNext(objectValidator::validate).log();
        return tbaPaisDtoMono.flatMap(tbaPaisDto-> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaPaisService.updateCountry(id, tbaPaisDto), TbaPais.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getCountryById(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaPaisService.getCountryById(id), TbaPais.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAllCountries(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaPaisService.getAllCountries(), TbaPais.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
