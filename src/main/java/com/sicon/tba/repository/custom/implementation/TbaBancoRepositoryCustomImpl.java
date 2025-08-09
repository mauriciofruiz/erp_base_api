package com.sicon.tba.repository.custom.implementation;

import com.sicon.tba.models.Periodo;
import com.sicon.tba.models.TbaBanco;
import com.sicon.tba.repository.custom.TbaBancoRepositoryCustom;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class TbaBancoRepositoryCustomImpl implements TbaBancoRepositoryCustom {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<TbaBanco> getBySearchParams(
            String nameBank,
            String numCount,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(nameBank, numCount, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    @Override
    public Mono<Long> getCountBySearchParams(
            String nameBank,
            String numCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(nameBank, numCount, null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            String nameBank,
            String numCount,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(nameBank, numCount, sortField, sortDirection, limit, offset, isCount));
        if (nameBank != null)
            spec = spec.bind("nameBank", nameBank);
        if (numCount != null)
            spec = spec.bind("numCount", numCount);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private String buildQueryForSearch(
            String nameBank,
            String numCount,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT banc.id_banco as idBanco, banc.nombre_banco as nombreBanco, banc.numerocuenta_banco as cuenta");
        queryBuilder.append(" FROM tba_banco banc");
        queryBuilder.append(" WHERE banc.estado_banco = true");
        if (nameBank != null)
            queryBuilder.append(" AND banc.nombre_banco LIKE CONCAT('%', :nameBank, '%')");
        if (numCount != null)
            queryBuilder.append(" AND banc.numerocuenta_banco LIKE CONCAT('%', :numCount, '%')");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private TbaBanco mapRowToEntity(Row row) {
        return TbaBanco.builder()
                .idBanco(row.get("idBanco", Integer.class))
                .nombreBanco(row.get("nombreBanco", String.class))
                .numeroCuentaBanco(row.get("cuenta", String.class))
                .build();
    }
}
