package erp.base.api.repository.custom;


import erp.base.api.dto.response.TbaTransporteResponse;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class TbaTransporteCustom {

    private final DatabaseClient databaseClient;

    public Flux<TbaTransporteResponse> getBySearchParams(
            String vehicleTypeName, String contactName, String sector, String province,
            Boolean internal, Boolean inactive,
            ZonedDateTime startDate,
            ZonedDateTime endDate,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(vehicleTypeName, contactName, sector, province, internal, inactive, startDate, endDate, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    public Mono<Long> getCountBySearchParams(
            String vehicleTypeName, String contactName, String sector, String province,
            Boolean internal, Boolean inactive,
            ZonedDateTime startDate,
            ZonedDateTime endDate
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(vehicleTypeName, contactName, sector, province, internal, inactive, startDate, endDate, null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private String buildQueryForSearch(
            String vehicleTypeName, String contactName, String sector, String province,
            Boolean internal, Boolean inactive,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(tr.*) FROM TRANSPORTE tr" : "SELECT tr.*, tv.* FROM TRANSPORTE tr");
        queryBuilder.append(" JOIN TIPOVEHICULO tv ON tr.ID_TIPOVEHICULO = tv.ID_TIPOVEHICULO");
        queryBuilder.append(" WHERE tr.fecha_creacion BETWEEN :startDate AND :endDate");
        if (vehicleTypeName != null)
            queryBuilder.append(" AND UPPER(tv.NOMBRE_TIPOVEHICULO) LIKE UPPER(CONCAT('%', :vehicleTypeName, '%'))");
        if (contactName != null)
            queryBuilder.append(" AND UPPER(tr.NOMBRECONTACTO_TRANSPORTE) LIKE UPPER(CONCAT('%', :contactName, '%'))");
        if (sector != null)
            queryBuilder.append(" AND UPPER(tr.SECTOR_TRANSPORTE) LIKE UPPER(CONCAT('%', :sector, '%'))");
        if (province != null)
            queryBuilder.append(" AND UPPER(tr.PROVINCIA_TRANSPORTE) LIKE UPPER(CONCAT('%', :province, '%'))");
        if (internal != null)
            queryBuilder.append(" AND tr.INTERNO_TRANSPORTE = :internal");
        if (inactive != null)
            queryBuilder.append(" AND tr.INACTIVO_TRANSPORTE = :inactive");
        if (sortField != null && sortDirection != null) {
            queryBuilder.append(" ORDER BY ");
            queryBuilder.append(sortField.equals("nombre_tipovehiculo") ? "tv." : "tr.");
            queryBuilder.append(sortField).append(" ").append(sortDirection);
        }
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            String vehicleTypeName, String contactName, String sector, String province,
            Boolean internal, Boolean inactive,
            ZonedDateTime startDate,
            ZonedDateTime endDate,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(vehicleTypeName, contactName, sector, province, internal, inactive, sortField, sortDirection, limit, offset, isCount))
                .bind("startDate", startDate)
                .bind("endDate", endDate);
        if (vehicleTypeName != null) spec = spec.bind("vehicleTypeName", vehicleTypeName);
        if (contactName != null) spec = spec.bind("contactName", contactName);
        if (sector != null) spec = spec.bind("sector", sector);
        if (province != null) spec = spec.bind("province", province);
        if (internal != null) spec = spec.bind("internal", internal);
        if (inactive != null) spec = spec.bind("inactive", inactive);
        if (sortField != null && sortDirection != null)
            spec = spec.bind("sortField", sortField)
                    .bind("sortDirection", sortDirection);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private TbaTransporteResponse mapRowToEntity(Row row) {
        return TbaTransporteResponse.builder()
                .idTransporte(row.get("id_transporte", Integer.class))
                .idTipoVehiculo(row.get("id_tipovehiculo", Integer.class))
                .nombreTipoVehiculo(row.get("nombre_tipovehiculo", String.class))
                .nombreContacto(row.get("nombrecontacto_transporte", String.class))
                .identificacionContacto(row.get("identcontacto_transporte", String.class))
                .numeroContacto(row.get("numcontacto_transporte", String.class))
                .placaTransporte(row.get("placa_transporte", String.class))
                .sectorTransporte(row.get("sector_transporte", String.class))
                .provinciaTransporte(row.get("provincia_transporte", String.class))
                .detalleTransporte(row.get("det_transporte", String.class))
                .pesoMinimoTransporte(row.get("pesomin_transporte", BigDecimal.class))
                .pesoMaximoTransporte(row.get("pesomax_transporte", BigDecimal.class))
                .matriculaTransporte(row.get("matricula_transporte", String.class))
                .internoTransporte(row.get("interno_transporte", Boolean.class))
                .inactivoTransporte(row.get("inactivo_transporte", Boolean.class))
                .idUsuarioCreacion(row.get("id_usuario_creacion", Integer.class))
                .idUsuarioModificacion(row.get("id_usuario_modificacion", Integer.class))
                .fechaCreacion(row.get("fecha_creacion", LocalDateTime.class))
                .fechaModificacion(row.get("fecha_modificacion", LocalDateTime.class))
                .build();
    }
}
