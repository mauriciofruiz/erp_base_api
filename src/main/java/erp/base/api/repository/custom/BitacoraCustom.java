package erp.base.api.repository.custom;

import erp.base.api.dto.BitacoraDto;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BitacoraCustom {

    private final DatabaseClient databaseClient;

    public Flux<BitacoraDto> getTransactionLogByClient(List<Integer> identifierList,
                                                       String sortField,
                                                       String sortDirection,
                                                       Integer limit,
                                                       Integer offset) {
        return databaseClient.sql(buildQueryForSearch(sortField, sortDirection, limit, offset, false))
                .bind("identifierList", identifierList)
                .bind("limit", limit)
                .bind("offset", offset)
                .map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    public Mono<Long> getCountTransactionLogByClient(List<Integer> identifierList) {
        return databaseClient.sql(buildQueryForSearch(null, null, null, null, true))
                .bind("identifierList", identifierList)
                .map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }


    private String buildQueryForSearch(String sortField,
                                       String sortDirection,
                                       Integer limit,
                                       Integer offset,
                                       boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        if (isCount)
            queryBuilder.append("SELECT COUNT(b.*)");
        else
            queryBuilder.append("""
                    SELECT b.ID_BITACORA AS logId, b.ID_USR as userId, b.IDENTIFICADOR_BITACORA as identifier, b.TABLE_BITACORA as table, b.CAMPO_BITACORA as field,\s
                        CASE\s
                            WHEN b.CAMPO_BITACORA = 'id_parroquia' THEN\s
                                (SELECT p.nombre_parroquia\s
                                 FROM PARROQUIA p\s
                                 WHERE p.id_parroquia = CAST(b.OLDVAL_BITACORA AS INTEGER))\s
                            WHEN b.CAMPO_BITACORA = 'id_pais' THEN\s
                                (SELECT pa.nombre_pais\s
                                 FROM PAIS pa\s
                                 WHERE pa.id_pais = CAST(b.OLDVAL_BITACORA AS INTEGER))\s
                            WHEN b.CAMPO_BITACORA = 'id_cuenta' THEN\s
                                (SELECT cc.nombre_cuenta\s
                                 FROM CUENTA cc\s
                                 WHERE cc.id_cuenta = CAST(b.OLDVAL_BITACORA AS INTEGER))\s
                            ELSE b.OLDVAL_BITACORA\s
                        END as oldValue,
                        CASE\s
                            WHEN b.CAMPO_BITACORA = 'id_parroquia' THEN\s
                                (SELECT p.nombre_parroquia\s
                                 FROM PARROQUIA p\s
                                 WHERE p.id_parroquia = CAST(b.NEWVAL_BITACORA AS INTEGER))\s
                            WHEN b.CAMPO_BITACORA = 'id_pais' THEN\s
                                (SELECT pa.nombre_pais\s
                                 FROM PAIS pa\s
                                 WHERE pa.id_pais = CAST(b.NEWVAL_BITACORA AS INTEGER))\s
                            WHEN b.CAMPO_BITACORA = 'id_cuenta' THEN\s
                                (SELECT cc.nombre_cuenta\s
                                 FROM CUENTA cc\s
                                 WHERE cc.id_cuenta = CAST(b.NEWVAL_BITACORA AS INTEGER))\s
                            ELSE b.NEWVAL_BITACORA\s
                        END as newValue, b.FECHA_BITACORA as date, b.IPDISPOSITIVO_BITACORA as deviceIp, b.NOMBREDISPOSITIVO_BITACORA as deviceName, p.nombre_persona as userName""");
        queryBuilder.append(" FROM bitacora b");
        queryBuilder.append(" LEFT JOIN USUARIO u on b.id_usr = u.id_usr");
        queryBuilder.append(" LEFT JOIN PERSONA p on u.id_persona = p.id_persona");
        queryBuilder.append(" where b.identificador_bitacora in (:identifierList)");
        queryBuilder.append(" and (b.table_bitacora = 'CLIENTE' or b.table_bitacora = 'PERSONA' or b.table_bitacora = 'CREDITOCLIENTE' or b.table_bitacora ='excepciones_cliente_descuento')");
        if (sortField != null && sortDirection != null) {
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        }
        if (limit != null && offset != null) queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private BitacoraDto mapRowToEntity(Row row) {
        return BitacoraDto.builder()
                .id(row.get("logId", Integer.class))
                .idUsuario(row.get("userId", Integer.class))
                .nombreUsuario(row.get("userName", String.class))
                .identificador(row.get("identifier", Integer.class))
                .tabla(row.get("table", String.class))
                .campo(row.get("field", String.class))
                .oldValue(row.get("oldValue", String.class))
                .newValue(row.get("newValue", String.class))
                .fecha(row.get("date", ZonedDateTime.class))
                .ip(row.get("deviceIp", String.class))
                .dispositivo(row.get("deviceName", String.class))
                .build();
    }
}
