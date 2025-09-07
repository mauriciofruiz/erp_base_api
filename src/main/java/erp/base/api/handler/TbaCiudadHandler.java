package erp.base.api.handler;

import erp.base.api.dto.TbaCiudadDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaCiudad;
import erp.base.api.service.TbaCiudadService;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los servicios de la tabla TbaCiudad
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class TbaCiudadHandler {

    private final TbaCiudadService tbaCiudadService;

    private final ObjectValidator objectValidator;

    /**
     * Método que crea una ciudad
     * @param request objeto de tipo ServerRequest
     * @return objeto de tipo ServerResponse
     */
    public Mono<ServerResponse> create(ServerRequest request){
        Mono<TbaCiudadDto> tbaCiudadDtoMono = request.bodyToMono(TbaCiudadDto.class).doOnNext(objectValidator::validate).log();
        return tbaCiudadDtoMono.flatMap(tbaCiudadDto -> ServerResponse.ok().body(tbaCiudadService.createCity(tbaCiudadDto), TbaCiudad.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });

    }

    /**
     * Método que actualiza una ciudad
     * @param request objeto de tipo ServerRequest
     * @return objeto de tipo ServerResponse
     */
    public Mono<ServerResponse> update (ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaCiudadDto> tbaCiudadDtoMono = request.bodyToMono(TbaCiudadDto.class).doOnNext(objectValidator::validate).log();
        return tbaCiudadDtoMono.flatMap(tbaCiudadDto -> ServerResponse.ok().body(tbaCiudadService.updateCity(id, tbaCiudadDto), TbaCiudad.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que consulta una ciudad por su id
     * @param request objeto de tipo ServerRequest
     * @return objeto de tipo ServerResponse
     */
    public Mono<ServerResponse> getById(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().body(tbaCiudadService.getCityById(id), TbaCiudad.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    public Mono<ServerResponse> getByParams(ServerRequest request){
        String provinceIdParam = request.queryParam("provinceId").orElse(null);
        String cityNameParam = request.queryParam("cityName").orElse(null);
        String cityCodeParam = request.queryParam("cityCode").orElse(null);
        return ServerResponse.ok().body(tbaCiudadService.getCityByParams(provinceIdParam, cityNameParam, cityCodeParam), TbaCiudad.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


}
