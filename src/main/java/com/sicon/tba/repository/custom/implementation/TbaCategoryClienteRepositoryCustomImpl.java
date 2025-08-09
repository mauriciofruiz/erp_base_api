package com.sicon.tba.repository.custom.implementation;

import com.sicon.tba.models.TbaCategoriaCliente;
import com.sicon.tba.repository.custom.DocumentoRepositoryCustom;
import com.sicon.tba.repository.custom.TbaCategoryClienteRepositoryCustom;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class TbaCategoryClienteRepositoryCustomImpl implements TbaCategoryClienteRepositoryCustom {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<TbaCategoriaCliente> getBySearchParams(String name, String sortField, String sortDirection, Integer limit, Integer offset) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(name, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    @Override
    public Mono<Long> getCountBySearchParams(String name) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(name, null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }


    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            String name,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(name, sortField, sortDirection, limit, offset, isCount));
        if (name != null)
            spec = spec.bind("name", name);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private String buildQueryForSearch(
            String name,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT *");
        queryBuilder.append(" FROM TBA_CATEGORIACLIENTE catCli");
        queryBuilder.append(" WHERE catCli.activocategoria_categoriacliente = true");
        if (name != null)
            queryBuilder.append(" AND UPPER(catCli.nombre_categoriacliente) LIKE UPPER(CONCAT('%',:name,'%'))");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private TbaCategoriaCliente mapRowToEntity(Row row) {
        return TbaCategoriaCliente.builder()
                .idCategoriaCliente(row.get("id_categoriacliente", Integer.class))
                .nombreCategoriaCliente(row.get("nombre_categoriacliente", String.class))
                .descuentoCategoriaCliente(row.get("descuento_categoriacliente", BigDecimal.class))
                .categoriaEspecialCategoriaCliente(row.get("categoriaespecial_categoriacliente", Boolean.class))
                .activoCategoriaCliente(row.get("activocategoria_categoriacliente", Boolean.class))
                .build();
    }
}
