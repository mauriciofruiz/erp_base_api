package erp.base.api.repository.custom

import erp.base.api.dto.BitacoraDto
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

@Component
class BitacoraCustom(private val databaseClient: DatabaseClient) {

    fun getTransactionLogByClient(
        identifierList: List<Int>,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<BitacoraDto> {
        var spec = databaseClient.sql(buildQueryForSearch(sortField, sortDirection, limit, offset, false))
            .bind("identifierList", identifierList)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    fun getCountTransactionLogByClient(identifierList: List<Int>): Mono<Long> =
        databaseClient.sql(buildQueryForSearch(null, null, null, null, true))
            .bind("identifierList", identifierList)
            .map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()

    private fun buildQueryForSearch(
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val selectPart = if (isCount) "SELECT COUNT(b.*)" else (
            "SELECT b.ID_BITACORA AS logId, b.ID_USR as userId, b.IDENTIFICADOR_BITACORA as identifier, b.TABLE_BITACORA as table, b.CAMPO_BITACORA as field, " +
                "CASE WHEN b.CAMPO_BITACORA = 'id_parroquia' THEN (SELECT p.nombre_parroquia FROM PARROQUIA p WHERE p.id_parroquia = CAST(b.OLDVAL_BITACORA AS INTEGER)) " +
                "WHEN b.CAMPO_BITACORA = 'id_pais' THEN (SELECT pa.nombre_pais FROM PAIS pa WHERE pa.id_pais = CAST(b.OLDVAL_BITACORA AS INTEGER)) " +
                "WHEN b.CAMPO_BITACORA = 'id_cuenta' THEN (SELECT cc.nombre_cuenta FROM CUENTA cc WHERE cc.id_cuenta = CAST(b.OLDVAL_BITACORA AS INTEGER)) ELSE b.OLDVAL_BITACORA END as oldValue, " +
                "CASE WHEN b.CAMPO_BITACORA = 'id_parroquia' THEN (SELECT p.nombre_parroquia FROM PARROQUIA p WHERE p.id_parroquia = CAST(b.NEWVAL_BITACORA AS INTEGER)) " +
                "WHEN b.CAMPO_BITACORA = 'id_pais' THEN (SELECT pa.nombre_pais FROM PAIS pa WHERE pa.id_pais = CAST(b.NEWVAL_BITACORA AS INTEGER)) " +
                "WHEN b.CAMPO_BITACORA = 'id_cuenta' THEN (SELECT cc.nombre_cuenta FROM CUENTA cc WHERE cc.id_cuenta = CAST(b.NEWVAL_BITACORA AS INTEGER)) ELSE b.NEWVAL_BITACORA END as newValue, " +
                "b.FECHA_BITACORA as date, b.IPDISPOSITIVO_BITACORA as deviceIp, b.NOMBREDISPOSITIVO_BITACORA as deviceName, p.nombre_persona as userName"
            )
        var q = selectPart + " FROM bitacora b LEFT JOIN USUARIO u on b.id_usr = u.id_usr LEFT JOIN PERSONA p on u.id_persona = p.id_persona"
        q += " where b.identificador_bitacora in (:identifierList)"
        q += " and (b.table_bitacora = 'CLIENTE' or b.table_bitacora = 'PERSONA' or b.table_bitacora = 'CREDITOCLIENTE' or b.table_bitacora ='excepciones_cliente_descuento')"
        if (sortField != null && sortDirection != null) q += " ORDER BY $sortField $sortDirection"
        if (limit != null && offset != null) q += " LIMIT :limit OFFSET :offset"
        return q
    }

    private fun mapRowToEntity(row: Row): BitacoraDto = BitacoraDto(
        id = row.get("logId", Int::class.javaObjectType),
        idUsuario = row.get("userId", Int::class.javaObjectType),
        nombreUsuario = row.get("userName", String::class.java),
        identificador = row.get("identifier", Int::class.javaObjectType),
        tabla = row.get("table", String::class.java),
        campo = row.get("field", String::class.java),
        oldValue = row.get("oldValue", String::class.java),
        newValue = row.get("newValue", String::class.java),
        fecha = row.get("date", ZonedDateTime::class.java),
        ip = row.get("deviceIp", String::class.java),
        dispositivo = row.get("deviceName", String::class.java)
    )
}
