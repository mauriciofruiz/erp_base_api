package com.sicon.tba.handler;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.ConCuentaDatosContablesDto;
import com.sicon.tba.dto.EmpleadoPersonaDto;
import com.sicon.tba.dto.TbaEmpleadoDto;
import com.sicon.tba.dto.TbaEmpleadoFichaDto;
import com.sicon.tba.dto.TbaEmployeeInformationDto;
import com.sicon.tba.dto.TbaInformacionContractualDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaEmpleado;
import com.sicon.tba.service.TbaEmpleadoService;
import com.sicon.tba.utils.Utils;
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
public class TbaEmpleadoHandler {

    private final TbaEmpleadoService tbaEmployeeService;
    private final ObjectValidator objectValidator;
    private final TbaPersonaHandler handler;

    public Mono<ServerResponse> createEmployee(ServerRequest request) {
        Mono<TbaEmployeeInformationDto> tbaEmpleadoDto = request.bodyToMono(TbaEmployeeInformationDto.class).doOnNext(objectValidator::validate).log();

        return tbaEmpleadoDto.flatMap(employee ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaEmployeeService.createEmployeePerson(employee.getEmpleadoDto(), employee.getInformacionContractualDto()), TbaEmpleado.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getFichaEmpleado(ServerRequest request) {
        String identification = null;
        String completeName = null;
        String paramSearch = request.queryParam("param").orElse(null);
        try {
            if (Utils.validateNumbers(paramSearch)) {
                identification = paramSearch;
            } else {
                completeName = paramSearch;
            }
        } catch (Exception e) {
            new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER);
        }

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaEmployeeService.getEmployee(completeName, identification), TbaEmpleadoFichaDto.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getCompleteEmployee(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaEmployeeService.getEmployeeRecord(id), TbaEmpleado.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getContractualInformation(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaEmployeeService.getContractualInformation(id), TbaInformacionContractualDto.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


    public Mono<ServerResponse> updateInformation(ServerRequest request) {
        Integer idEmpleado = Integer.valueOf(request.pathVariable("id"));

        Mono<TbaEmpleadoDto> empleadoDtoMono = request.bodyToMono(TbaEmpleadoDto.class).doOnNext(objectValidator::validate).log();
        return empleadoDtoMono.flatMap(employe ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(tbaEmployeeService.updateEmployeeRecord(idEmpleado, employe), TbaEmpleado.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });

    }

    public Mono<ServerResponse> updateContractualInformation(ServerRequest request) {
        Integer idEmpleado = Integer.valueOf(request.pathVariable("id"));

        Mono<TbaInformacionContractualDto> empleadoDtoMono = request.bodyToMono(TbaInformacionContractualDto.class).doOnNext(objectValidator::validate).log();
        return empleadoDtoMono.flatMap(informationContractual -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaEmployeeService.updateContractualInformation(idEmpleado, informationContractual), TbaEmpleado.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAccountingDataEmployee(ServerRequest request) {
        Integer idEmpleado = Integer.valueOf(request.pathVariable("id"));

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(tbaEmployeeService.getAccountingDataEmployee(idEmpleado), ConCuentaDatosContablesDto.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getAllEmployeeActive(ServerRequest request) {
        String paramSearch = request.queryParam("param").orElse(null);
        if (paramSearch != null) {
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaEmployeeService.getEmployeeActiveByParam(paramSearch), EmpleadoPersonaDto.class);
        }
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaEmployeeService.getEmployeeActive(), EmpleadoPersonaDto.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getEmpleadoPersonaById(ServerRequest request) {
        Integer id = Integer.valueOf(request.queryParam("id").orElse("0"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tbaEmployeeService.getEmpleadoPersonaById(id), EmpleadoPersonaDto.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


}
