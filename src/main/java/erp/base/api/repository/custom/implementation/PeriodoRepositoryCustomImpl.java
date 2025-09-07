package erp.base.api.repository.custom.implementation;

import erp.base.api.models.Periodo;
import erp.base.api.repository.custom.PeriodoRepositoryCustom;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class PeriodoRepositoryCustomImpl implements PeriodoRepositoryCustom {
    private final DatabaseClient databaseClient;

    @Override
    public Flux<Periodo> getBySearchParams(
            Integer moduleId,
            ZonedDateTime startDate,
            ZonedDateTime endDate,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(moduleId, startDate, endDate, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    @Override
    public Mono<Long> getCountBySearchParams(
            Integer moduleId,
            ZonedDateTime startDate,
            ZonedDateTime endDate
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(moduleId, startDate, endDate, null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            Integer moduleId,
            ZonedDateTime startDate,
            ZonedDateTime endDate,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(moduleId, sortField, sortDirection, limit, offset, isCount))
                .bind("startDate", startDate)
                .bind("endDate", endDate);
        if(moduleId != null)
            spec = spec.bind("moduleId", moduleId);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private String buildQueryForSearch(
            Integer moduleId,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT per.id_periodo as idPeriodo, per.fechacierre_periodo as periodoCierre, per.fechaanterior_periodo as periodoAnterior, per.id_modulo as idModulo");
        queryBuilder.append(" FROM con_periodo per");
        queryBuilder.append(" WHERE per.fechacierre_periodo BETWEEN :startDate AND :endDate");
        if (moduleId != null)
            queryBuilder.append(" AND per.id_modulo = :moduleId");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private Periodo mapRowToEntity(Row row) {
        return Periodo.builder()
                .idPeriodo(row.get("idPeriodo", Integer.class))
                .idModulo(row.get("idModulo", Integer.class))
                .fechaCierrePeriodo(row.get("periodoCierre", LocalDate.class))
                .fechaAnteriorPeriodo(row.get("periodoAnterior", LocalDate.class))
                .build();
    }
}
