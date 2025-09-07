package erp.base.api.handler;

import erp.base.api.exception.CustomException;
import erp.base.api.models.Caja;
import erp.base.api.models.Periodo;
import erp.base.api.service.PeriodoService;
import erp.base.api.utils.Utils;
import erp.base.api.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
@Log4j2
public class PeriodoHandler {
    private final PeriodoService periodoService;
    private final WebProperties.Resources resources;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<Periodo> periodoMono = request.bodyToMono(Periodo.class).doOnNext(objectValidator::validate).log();
        return periodoMono.flatMap(periodo ->
                        ServerResponse.ok().body(periodoService.createPeriodo(periodo), Periodo.class))
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getLastPeriods(ServerRequest serverRequest) {
        return ServerResponse.ok().body(periodoService.getLastPeriods(), Periodo.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }

    public Mono<ServerResponse> getRequestBySearchParams(ServerRequest request) {
        String moduleIdParam = request.queryParam("moduleId").orElse(null);
        Integer moduleId = (moduleIdParam == null || moduleIdParam.isEmpty()) ? null : Integer.valueOf(moduleIdParam);
        String startDate = request.queryParam("startDate").orElse(null);
        ZonedDateTime startDateParam = startDate != null ? Utils.parseStringToZonedDateTime(startDate) : null;
        String endDate = request.queryParam("endDate").orElse(null);
        ZonedDateTime endDateParam = endDate != null ? Utils.parseStringToZonedDateTime(endDate) : null;
        Integer page = Integer.valueOf(request.queryParam("page").orElse("1"));
        Integer limit = Integer.valueOf(request.queryParam("limit").orElse("10"));
        String sortDirection = request.queryParam("sortDirection").orElse(null);
        String sortField = request.queryParam("sortField").orElse(null);

        if (request.queryParams().isEmpty()) {
            return getLastPeriods(request);
        }
        return ServerResponse.ok().body(periodoService.getRequestBySearchParams(moduleId, startDateParam, endDateParam, page, limit, sortDirection, sortField), Periodo.class)
                .onErrorMap(e -> {
                    log.error(e.getMessage());
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }
}
