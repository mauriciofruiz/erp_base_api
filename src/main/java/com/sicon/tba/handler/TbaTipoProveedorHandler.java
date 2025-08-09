package com.sicon.tba.handler;

import com.sicon.tba.dto.TbaTipoProveedorDto;
import com.sicon.tba.dto.TrbTipoContribuyenteDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaTipoProveedor;
import com.sicon.tba.models.TrbTipoContribuyente;
import com.sicon.tba.service.TbaTipoProveedorService;
import com.sicon.tba.validation.ObjectValidator;
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
public class TbaTipoProveedorHandler {


    private final TbaTipoProveedorService tbaTipoProveedorService;

    private final ObjectValidator objectValidator;

    /**
     * Método que crea un tipo de proveedor
     *
     * @param serverRequest petición
     * @return tipo de proveedor creado
     */
    public Mono<ServerResponse> createVendorType(ServerRequest serverRequest) {
        Mono<TbaTipoProveedorDto> tbaTipoProveedorDtoMono = serverRequest.bodyToMono(TbaTipoProveedorDto.class).doOnNext(objectValidator::validate).log();
        return tbaTipoProveedorDtoMono.flatMap(tbaTipoProveedorDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaTipoProveedorService.createVendorType(tbaTipoProveedorDto), TbaTipoProveedor.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    /**
     * Método que obtiene un tipo de proveedor por id
     *
     * @param serverRequest petición
     * @return tipo de proveedor
     */
    public Mono<ServerResponse> getVendorTypeById(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaTipoProveedorService.getVendorTypeById(id), TbaTipoProveedor.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que obtiene todos los tipos de proveedor
     *
     * @param serverRequest petición
     * @return tipos de proveedor
     */
    public Mono<ServerResponse> getAllVendorTypes(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaTipoProveedorService.getAllVendorTypes(), TbaTipoProveedor.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que elimina un tipo de proveedor
     * @param serverRequest petición
     * @return
     */
    public Mono<ServerResponse> deleteVendorType(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        //Se devuelve un no content si la acción se ha ejecutado y no se debe proporcionar más información como en este caso
        return tbaTipoProveedorService.deleteVendorType(id).then(ServerResponse.noContent().build())
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
