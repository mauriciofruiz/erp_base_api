package com.sicon.tba.repository.custom;

import com.sicon.tba.dto.TbaMenuDto;
import com.sicon.tba.enums.TipoBodegaEnum;
import com.sicon.tba.models.Sucursal;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TbaSucursalCustom {

    private final DatabaseClient databaseClient;

    public Flux<Sucursal> getBySearchParams(
            Integer cityId,
            Integer regionId,
            String categoryBranchId,
            String name,
            String mnemonic,
            Boolean isOutlet,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(cityId, regionId, categoryBranchId, name, mnemonic, isOutlet, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    public Mono<Long> getCountBySearchParams(
            Integer cityId,
            Integer regionId,
            String categoryBranchId,
            String name,
            String mnemonic,
            Boolean isOutlet
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(cityId, regionId, categoryBranchId, name, mnemonic, isOutlet, null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            Integer cityId,
            Integer regionId,
            String categoryBranchId,
            String name,
            String mnemonic,
            Boolean isOutlet,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(cityId, regionId, categoryBranchId, name, mnemonic, isOutlet, sortField, sortDirection, limit, offset, isCount));
        if (cityId != null)
            spec = spec.bind("cityId", cityId);
        if (regionId != null)
            spec = spec.bind("regionId", regionId);
        if (categoryBranchId != null)
            spec = spec.bind("categoryBranchId", categoryBranchId);
        if (name != null)
            spec = spec.bind("name", name);
        if (mnemonic != null)
            spec = spec.bind("mnemonic", mnemonic);
        if (isOutlet)
            spec = spec.bind("isOutlet", isOutlet);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private String buildQueryForSearch(
            Integer cityId,
            Integer regionId,
            String categoryBranchId,
            String name,
            String mnemonic,
            Boolean isOutlet,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT DISTINCT su.id_sucursal, su.*");
        queryBuilder.append(" FROM TBA_SUCURSAL su");
        queryBuilder.append(" WHERE su.estado_sucursal = true");
        if (cityId != null)
            queryBuilder.append(" AND su.id_ciudad = :cityId");
        if (regionId != null)
            queryBuilder.append(" AND su.id_region = :regionId");
        if (categoryBranchId != null)
            queryBuilder.append(" AND UPPER(su.id_categoriasucursal) LIKE UPPER(CONCAT('%',:categoryBranchId,'%'))");
        if (name != null)
            queryBuilder.append(" AND UPPER(su.nombre_sucursal) LIKE UPPER(CONCAT('%',:name,'%'))");
        if (mnemonic != null)
            queryBuilder.append(" AND UPPER(su.nemonico_sucursal) LIKE UPPER(CONCAT('%',:mnemonic,'%'))");
        if (isOutlet)
            queryBuilder.append(" AND su.esoutlet_sucursal = :isOutlet");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    public Flux<Sucursal> getBranchProduction() {
        StringBuilder queryBuilder = new StringBuilder("SELECT DISTINCT su.id_sucursal, su.*");
        queryBuilder.append(" FROM TBA_SUCURSAL su");
        queryBuilder.append(" JOIN inv_bodega bo ON su.id_sucursal = bo.id_sucursal");
        queryBuilder.append(" JOIN inv_tipo_bodega tbo on tbo.id_tipo_bodega = bo.id_tipo_bodega");
        queryBuilder.append(" WHERE tbo.id_tipo_bodega = :warehouseTypeId");
        return databaseClient.sql(queryBuilder.toString())
                .bind("warehouseTypeId", TipoBodegaEnum.PRODUCCION.getIdTipoBodega())
                .map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    private Sucursal mapRowToEntity(Row row) {
        return Sucursal.builder()
                .idSucursal(row.get("id_sucursal", Integer.class))
                .idCiudad(row.get("id_ciudad", Integer.class))
                .idCategoriaSucursal(row.get("id_categoriasucursal", String.class))
                .idRegion(row.get("id_region", Integer.class))
                .nombreSucursal(row.get("nombre_sucursal", String.class))
                .nemonicoSucursal(row.get("nemonico_sucursal", String.class))
                .direccionSucursal(row.get("direccion_sucursal", String.class))
                .telefonoSucursal(row.get("telefono_sucursal", String.class))
                .telefono2Sucursal(row.get("telefono2_sucursal", String.class))
                .celularSucursal(row.get("celular_sucursal", String.class))
                .correoSucursal(row.get("correo_sucursal", String.class))
                .correo2Sucursal(row.get("correo2_sucursal", String.class))
                .responsableSucursal(row.get("responsable_sucursal", String.class))
                .esOutletSucursal(row.get("esoutlet_sucursal", Boolean.class))
                .estadoSucursal(row.get("estado_sucursal", Boolean.class))
                .impresionTermicaSucursal(row.get("impresiontermica_sucursal", Boolean.class))
                .codigoSucursal(row.get("codigo_sucursal", String.class))
                .build();
    }
}
