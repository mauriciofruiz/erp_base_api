package com.sicon.tba.repository.custom.implementation;

import com.sicon.tba.models.TbaRol;
import com.sicon.tba.repository.custom.TbaRolRepositoryCustom;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TbaRolRepositoryCustomImpl implements TbaRolRepositoryCustom {
    private final DatabaseClient databaseClient;

    @Override
    public Flux<TbaRol> getBySearchParams(
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    @Override
    public Mono<Long> getCountBySearchParams() {

        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private String buildQueryForSearch(
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT rol.id_rol as idRol, rol.nombre_rol as nameRol, rol.descripcion_rol as descriptionRol");
        queryBuilder.append(" FROM tba_rol rol");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
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

    private TbaRol mapRowToEntity(Row row) {
        return TbaRol.builder()
                .idRol(row.get("idRol", Integer.class))
                .nombreRol(row.get("nameRol", String.class))
                .descripcionRol(row.get("descriptionRol", String.class))
                .build();
    }
}
