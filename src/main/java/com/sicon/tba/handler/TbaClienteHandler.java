package com.sicon.tba.handler;

import com.sicon.tba.constants.Constants;
import com.sicon.tba.dto.response.PersonaClienteCreditoDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaCliente;
import com.sicon.tba.service.TbaClienteService;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Log4j2
public class TbaClienteHandler {

    private final TbaClienteService tbaClienteService;

    /**
     * Método que crea un cliente
     * @param request objeto de tipo ServerRequest
     * @return objeto de tipo ServerResponse
     */
    public Mono<ServerResponse> create(ServerRequest request){
        Mono<PersonaClienteCreditoDto> tbaClienteDtoMono = request.bodyToMono(PersonaClienteCreditoDto.class).log();
        return tbaClienteDtoMono.flatMap((tbaClienteDto -> ServerResponse.ok().body(tbaClienteService.createCustomer(tbaClienteDto), Server.class)))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que actualiza un cliente
     * @param request objeto de tipo ServerRequest
     * @return objeto de tipo ServerResponse
     */
    public Mono<ServerResponse> update(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Mono<PersonaClienteCreditoDto> tbaClienteDtoMono = request.bodyToMono(PersonaClienteCreditoDto.class).log();
        return  tbaClienteDtoMono.flatMap(tbaClienteDto -> ServerResponse.ok().body(tbaClienteService.updateClient(id, tbaClienteDto), Server.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que consulta un cliente por su id
     * @param request objeto de tipo ServerRequest
     * @return objeto de tipo ServerResponse
     */
    public Mono<ServerResponse> getById(ServerRequest request){
        Integer id = Integer.valueOf(request.pathVariable("id"));
        Integer userId = Integer.parseInt(Objects.requireNonNull(request.headers().firstHeader(Constants.USER_ID_HEADER)));
        return ServerResponse.ok().body(tbaClienteService.findClientById(id, userId), PersonaClienteCreditoDto.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    /**
     * Método que consulta un cliente por params
     * @param request objeto de tipo ServerRequest
     * @return objeto de tipo ServerResponse
     */
    public Mono<ServerResponse> getByParams(ServerRequest request){
        String nameClientParam = request.queryParam("clientCommercialName").orElse(null);
        String categoryClientIDParam = request.queryParam("clientCategoryID").orElse(null);
        String identificationClientParam = request.queryParam("clientIdentification").orElse(null);
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        Integer categoryIDParam = categoryClientIDParam != null ? Integer.valueOf(categoryClientIDParam) : null;

        return  ServerResponse.ok().body(tbaClienteService.getCustomerByParams(nameClientParam, categoryIDParam, identificationClientParam,page,limit ), TbaCliente.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> verifyClientExistsByIdentification(ServerRequest request) {
        String identification = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaClienteService.verifyClientExistsByIdentification(identification), String.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
