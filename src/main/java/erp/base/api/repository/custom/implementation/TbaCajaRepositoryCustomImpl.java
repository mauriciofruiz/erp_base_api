package erp.base.api.repository.custom.implementation;

import erp.base.api.models.Caja;
import erp.base.api.models.TbaCategoriaCliente;
import erp.base.api.repository.custom.TbaCajaRepositoryCustom;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class TbaCajaRepositoryCustomImpl implements TbaCajaRepositoryCustom {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<Caja> getBySearchParams(
            Integer branchId,
            String name,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(branchId, name, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    @Override
    public Mono<Long> getCountBySearchParams(
            Integer branchId,
            String name
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(branchId, name, null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            Integer branchId,
            String name,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(branchId, name, sortField, sortDirection, limit, offset, isCount));
        if (branchId != null)
            spec = spec.bind("branchId", branchId);
        if (name != null)
            spec = spec.bind("name", name);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private String buildQueryForSearch(
            Integer branchId,
            String name,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT caj.id_caja as idCaja, caj.id_sucursal as idSucursal, caj.nombre_caja as nombreCaja, caj.estado_caja as estadoCaja");
        queryBuilder.append(" FROM TBA_CAJA caj");
        queryBuilder.append(" WHERE caj.estado_caja = true");
        if (branchId != null)
            queryBuilder.append(" AND caj.id_sucursal = :branchId");
        if (name != null)
            queryBuilder.append(" AND UPPER(caj.nombre_caja) LIKE UPPER(CONCAT('%',:name,'%'))");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private Caja mapRowToEntity(Row row) {
        return Caja.builder()
                .idCaja(row.get("idCaja", Integer.class))
                .idSucursal(row.get("idSucursal", Integer.class))
                .nombreCaja(row.get("nombreCaja", String.class))
                .estadoCaja(row.get("estadoCaja", Boolean.class))
                .build();
    }
}
