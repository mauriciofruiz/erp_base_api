package erp.base.api.repository.custom;

import erp.base.api.models.Periodo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

public interface PeriodoRepositoryCustom {
    Flux<Periodo> getBySearchParams(
            Integer moduleId,
            ZonedDateTime startDate,
            ZonedDateTime endDate,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    );

    Mono<Long> getCountBySearchParams(
            Integer moduleId,
            ZonedDateTime startDate,
            ZonedDateTime endDate
    );
}
