package erp.base.api.handler;

import erp.base.api.dto.TbaTipoCuentaBancariaDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaTipoCuentaBancaria;
import erp.base.api.service.TbaTipoCuentaBancariaService;
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
public class TbaTipoCuentaBancariaHandler {

    private final TbaTipoCuentaBancariaService tbaCategoriaSucursalService;
    private final ObjectValidator objectValidator;

    /**
     * Método que crea un tipo de cuenta
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos del tipo de cuenta
     */

    public Mono<ServerResponse> create(ServerRequest request){
        Mono<TbaTipoCuentaBancariaDto> tipoCuentaBancariaDtoMono = request.bodyToMono(TbaTipoCuentaBancariaDto.class).doOnNext(objectValidator::validate).log();
        return tipoCuentaBancariaDtoMono.flatMap(tbaTipoCuentaBancariaDto -> ServerResponse.ok().body(tbaCategoriaSucursalService.createBankAccountType(tbaTipoCuentaBancariaDto), TbaTipoCuentaBancaria.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza el tipo de cuenta
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos del tipo de contacto
     */

    public Mono<ServerResponse> update(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<TbaTipoCuentaBancariaDto> tipoCuentaBancariaDtoMono = request.bodyToMono(TbaTipoCuentaBancariaDto.class).doOnNext(objectValidator::validate).log();
        return  tipoCuentaBancariaDtoMono.flatMap(tbaTipoCuentaBancariaDto ->
                ServerResponse.ok().body(tbaCategoriaSucursalService.updateBankAccountType(id, tbaTipoCuentaBancariaDto), TbaTipoCuentaBancariaDto.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    /**
     * Método que permite consulta un tipo de cuenta por su id
     * @param request Objeto con los datos de la petición
     * @return Objeto con los datos de tipo de cuenta
     */

    public Mono<ServerResponse> getById(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaCategoriaSucursalService.getById(id), TbaTipoCuentaBancariaDto.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    /**
     * Método que permite consulta todos los tipos de cuenta
     * @param
     * @return Objeto con los datos de tipo de cuenta
     */

    public Mono<ServerResponse> getAll(ServerRequest request){
        return  ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaCategoriaSucursalService.getAllBankAccountType(), TbaTipoCuentaBancariaDto.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
