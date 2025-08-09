package com.sicon.tba.repository.custom.implementation;

import com.sicon.tba.models.TbaBanco;
import com.sicon.tba.models.TbaBancoCatalogo;
import com.sicon.tba.repository.custom.TbaBancoCatalogoRespositoryCustom;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TbaBancoCatalogoRespositoryCustomImpl implements TbaBancoCatalogoRespositoryCustom {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<TbaBancoCatalogo> getBySearchParams(
            String bankCatalogName,
            String bankCatalogCode,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(bankCatalogName, bankCatalogCode, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    @Override
    public Mono<Long> getCountBySearchParams(
            String bankCatalogName,
            String bankCatalogCode
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(bankCatalogName, bankCatalogCode, null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            String bankCatalogName,
            String bankCatalogCode,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(bankCatalogName, bankCatalogCode, sortField, sortDirection, limit, offset, isCount));
        if (bankCatalogName != null)
            spec = spec.bind("bankCatalogName", bankCatalogName);
        if (bankCatalogCode != null)
            spec = spec.bind("bankCatalogCode", bankCatalogCode);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private String buildQueryForSearch(
            String bankCatalogName,
            String bankCatalogCode,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT cat.id_bancocatalogo as idBancoCat, cat.nombre_bancocatalogo as nombreCatBanco, cat.codigo_bancocatalogo as codigoCat");
        queryBuilder.append(" FROM tba_bancocatalogo cat");
        queryBuilder.append(" WHERE cat.estado_banco = true");
        if (bankCatalogName != null)
            queryBuilder.append(" AND cat.nombre_bancocatalogo LIKE CONCAT('%', :bankCatalogName, '%')");
        if (bankCatalogCode != null)
            queryBuilder.append(" AND cat.codigo_bancocatalogo LIKE CONCAT('%', :bankCatalogCode, '%')");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private TbaBancoCatalogo mapRowToEntity(Row row) {
        return TbaBancoCatalogo.builder()
                .idBancoCatalogo(row.get("idBancoCat", Integer.class))
                .nombreBancoCatalogo(row.get("nombreCatBanco", String.class))
                .codigoBancoCatalogo(row.get("codigoCat", String.class))
                .build();
    }


}
