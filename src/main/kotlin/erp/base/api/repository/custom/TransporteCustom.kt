package erp.base.api.repository.custom

import erp.base.api.dto.response.TransporteResponse
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Component
class TransporteCustom(private val databaseClient: DatabaseClient) {

    fun getBySearchParams(
        vehicleTypeName: String?,
        contactName: String?,
        sector: String?,
        province: String?,
        internal: Boolean?,
        inactive: Boolean?,
        startDate: ZonedDateTime?,
        endDate: ZonedDateTime?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<TransporteResponse> {
        var spec = databaseClient.sql(buildQueryForSearch(vehicleTypeName, contactName, sector, province, internal, inactive, sortField, sortDirection, limit, offset, false))
            .bind("startDate", startDate)
            .bind("endDate", endDate)
        if (vehicleTypeName != null) spec = spec.bind("vehicleTypeName", vehicleTypeName)
        if (contactName != null) spec = spec.bind("contactName", contactName)
        if (sector != null) spec = spec.bind("sector", sector)
        if (province != null) spec = spec.bind("province", province)
        if (internal != null) spec = spec.bind("internal", internal)
        if (inactive != null) spec = spec.bind("inactive", inactive)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    fun getCountBySearchParams(
        vehicleTypeName: String?,
        contactName: String?,
        sector: String?,
        province: String?,
        internal: Boolean?,
        inactive: Boolean?,
        startDate: ZonedDateTime?,
        endDate: ZonedDateTime?
    ): Mono<Long> {
        var spec = databaseClient.sql(buildQueryForSearch(vehicleTypeName, contactName, sector, province, internal, inactive, null, null, null, null, true))
            .bind("startDate", startDate)
            .bind("endDate", endDate)
        if (vehicleTypeName != null) spec = spec.bind("vehicleTypeName", vehicleTypeName)
        if (contactName != null) spec = spec.bind("contactName", contactName)
        if (sector != null) spec = spec.bind("sector", sector)
        if (province != null) spec = spec.bind("province", province)
        if (internal != null) spec = spec.bind("internal", internal)
        if (inactive != null) spec = spec.bind("inactive", inactive)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        vehicleTypeName: String?,
        contactName: String?,
        sector: String?,
        province: String?,
        internal: Boolean?,
        inactive: Boolean?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (isCount) "SELECT COUNT(tr.*) FROM TRANSPORTE tr" else "SELECT tr.*, tv.* FROM TRANSPORTE tr")
        q.append(" JOIN TIPOVEHICULO tv ON tr.ID_TIPOVEHICULO = tv.ID_TIPOVEHICULO")
        q.append(" WHERE tr.fecha_creacion BETWEEN :startDate AND :endDate")
        if (vehicleTypeName != null) q.append(" AND UPPER(tv.NOMBRE_TIPOVEHICULO) LIKE UPPER(CONCAT('%', :vehicleTypeName, '%'))")
        if (contactName != null) q.append(" AND UPPER(tr.NOMBRECONTACTO_TRANSPORTE) LIKE UPPER(CONCAT('%', :contactName, '%'))")
        if (sector != null) q.append(" AND UPPER(tr.SECTOR_TRANSPORTE) LIKE UPPER(CONCAT('%', :sector, '%'))")
        if (province != null) q.append(" AND UPPER(tr.PROVINCIA_TRANSPORTE) LIKE UPPER(CONCAT('%', :province, '%'))")
        if (internal != null) q.append(" AND tr.INTERNO_TRANSPORTE = :internal")
        if (inactive != null) q.append(" AND tr.INACTIVO_TRANSPORTE = :inactive")
        if (sortField != null && sortDirection != null) {
            q.append(" ORDER BY ")
            q.append(if (sortField == "nombre_tipovehiculo") "tv." else "tr.")
            q.append(sortField).append(" ").append(sortDirection)
        }
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): TransporteResponse = TransporteResponse(
        idUsuarioCreacion = row.get("id_usuario_creacion", Int::class.javaObjectType),
        idUsuarioModificacion = row.get("id_usuario_modificacion", Int::class.javaObjectType),
        fechaCreacion = row.get("fecha_creacion", LocalDateTime::class.java),
        fechaModificacion = row.get("fecha_modificacion", LocalDateTime::class.java),
        idTransporte = row.get("id_transporte", Int::class.javaObjectType),
        idTipoVehiculo = row.get("id_tipovehiculo", Int::class.javaObjectType),
        nombreTipoVehiculo = row.get("nombre_tipovehiculo", String::class.java),
        nombreContacto = row.get("nombrecontacto_transporte", String::class.java),
        identificacionContacto = row.get("identcontacto_transporte", String::class.java),
        numeroContacto = row.get("numcontacto_transporte", String::class.java),
        placaTransporte = row.get("placa_transporte", String::class.java),
        sectorTransporte = row.get("sector_transporte", String::class.java),
        provinciaTransporte = row.get("provincia_transporte", String::class.java),
        detalleTransporte = row.get("det_transporte", String::class.java),
        pesoMinimoTransporte = row.get("pesomin_transporte", BigDecimal::class.java),
        pesoMaximoTransporte = row.get("pesomax_transporte", BigDecimal::class.java),
        matriculaTransporte = row.get("matricula_transporte", String::class.java),
        internoTransporte = row.get("interno_transporte", Boolean::class.javaObjectType),
        inactivoTransporte = row.get("inactivo_transporte", Boolean::class.javaObjectType)
    )
}
