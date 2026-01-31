package erp.base.api.repository.custom.implementation;

import erp.base.api.models.TbaFormaPagoCobro;
import erp.base.api.repository.custom.TbaFormaPagoRepositoryCustom;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class TbaFormaPagoRepositoryCustomImpl implements TbaFormaPagoRepositoryCustom {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<TbaFormaPagoCobro> getBySearchParams(String sortField, String sortDirection, Integer limit, Integer offset) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    @Override
    public Mono<Long> getCountBySearchParams() {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(sortField, sortDirection, limit, offset, isCount));
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private String buildQueryForSearch(
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT *");
        queryBuilder.append(" FROM FORMAPAGOCOBRO");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private TbaFormaPagoCobro mapRowToEntity(Row row) {
        return TbaFormaPagoCobro.builder()
                .idFormaPagoCobro(row.get("id_formapagocobro", String.class))
                .nombreFormaPagoCobro(row.get("nombre_formapagocobro", String.class))
                .aliasFormaPagoCobro(row.get("alias_formapagocobro", String.class))
                .descuentoFormaPagoCobro(row.get("descuento_formapagocobro", BigDecimal.class))
                .codigoFormaPagoCobro(row.get("cod_formapagocobro", String.class))
                .estadoFormaPagoCobro(row.get("estado_formapagocobro", Boolean.class))
                .build();
    }
}
