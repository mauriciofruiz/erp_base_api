package erp.base.api.handler;

import erp.base.api.dto.response.PersonaClienteResponse;
import erp.base.api.dto.response.TbaTransporteResponse;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaTransporte;
import erp.base.api.service.TbaTransporteService;
import erp.base.api.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.Objects;

@Log4j2
@Component
@RequiredArgsConstructor
public class TbaTransporteHandler {
    private final TbaTransporteService transporteService;


    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(TbaTransporte.class)
                .flatMap(transporteService::createTransportation)
                .then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("Transacción creada correctamente"))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error al crear la transacción"));

    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Integer id = Integer.valueOf(Objects.requireNonNull(request.pathVariable("id")));
        return request.bodyToMono(TbaTransporte.class)
                .flatMap(transporte -> transporteService.updateTransportation(id, transporte))
                .then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("Transacción actualizada correctamente"))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error al actualizar la transacción"));
    }

    public Mono<ServerResponse> patch(ServerRequest request) {
        Integer id = Integer.valueOf(Objects.requireNonNull(request.pathVariable("id")));
        return request.bodyToMono(TbaTransporte.class)
                .flatMap(transporte -> transporteService.patchTransportation(id, transporte))
                .flatMap(transporte -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(transporte));
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        Integer id = Integer.valueOf(Objects.requireNonNull(request.pathVariable("id")));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transporteService.getTransportationById(id), TbaTransporteResponse.class);
    }

    public Mono<ServerResponse> getBySearchParams(ServerRequest request) {
        String vehicleTypeName = request.queryParam("vehicleTypeName").orElse(null);
        String contactName = request.queryParam("contactName").orElse(null);
        String sector = request.queryParam("sector").orElse(null);
        String province = request.queryParam("province").orElse(null);
        Boolean internal = Boolean.valueOf(request.queryParam("internal").orElse("false"));
        Boolean inactive = Boolean.valueOf(request.queryParam("inactive").orElse("false"));
        String startDate = request.queryParam("startDate").orElse(null);
        ZonedDateTime startDateParam = startDate != null ? Utils.parseStringToZonedDateTime(startDate) : null;
        String endDate = request.queryParam("endDate").orElse(null);
        ZonedDateTime endDateParam = endDate != null ? Utils.parseStringToZonedDateTime(endDate) : null;
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);
        Boolean byCurrentMonth = Boolean.valueOf(request.queryParam("byCurrentMonth").orElse("false"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transporteService.getTransportationsBySearchParams(vehicleTypeName, contactName, sector, province, internal, inactive, startDateParam, endDateParam, byCurrentMonth, page, limit, sortDirection, sortField), TbaTransporteResponse.class)
                .onErrorMap(e -> new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
    }

    public Mono<ServerResponse> getAllActiveTransportistas(ServerRequest request) {
        String paramSearch = request.queryParam("param").orElse(null);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transporteService.getAllActiveTransportistas(paramSearch), TbaTransporte.class).onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getTransportationByParams(ServerRequest request) {
        String ci = request.queryParam("ci").orElse(null);
        String placa = request.queryParam("placa").orElse(null);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(transporteService.getTransportationByCiAndPlaca(ci, placa), TbaTransporte.class);
    }
}
