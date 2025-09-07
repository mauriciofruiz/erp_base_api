package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Caja;
import erp.base.api.models.Periodo;
import erp.base.api.models.Rol;
import erp.base.api.repository.PeriodoRepository;
import erp.base.api.repository.RolRepository;
import erp.base.api.utils.Paginacion;
import erp.base.api.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class PeriodoService {

    private final PeriodoRepository periodoRepository;

    public Mono<Periodo> createPeriodo(Periodo periodo) {
        Periodo newPeriodo = Periodo.builder()
                .fechaCierrePeriodo(periodo.getFechaCierrePeriodo())
                .fechaAnteriorPeriodo(periodo.getFechaAnteriorPeriodo())
                .idModulo(periodo.getIdModulo())
                .build();
        return periodoRepository.save(newPeriodo);
    }

    public Flux<Periodo> getLastPeriods() {
        return periodoRepository.getLastPeriods();
    }

    public Mono<Paginacion<Periodo>> getRequestBySearchParams(
            Integer moduleId,
            ZonedDateTime startDateParam,
            ZonedDateTime endDateParam,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        ZonedDateTime endDate = Utils.getEndDateForPagination(false, endDateParam);
        ZonedDateTime startDate = Utils.getStartDateForPagination(false, startDateParam);
        Flux<Periodo> periodoFlux = periodoRepository.getBySearchParams(moduleId, startDate, endDate, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = periodoRepository.getCountBySearchParams(moduleId, startDate, endDate);
        return total.flatMap(totalCount -> periodoFlux.collectList().map(cajaList -> new Paginacion<>(cajaList, page, limit, totalCount.intValue())));
    }
}
