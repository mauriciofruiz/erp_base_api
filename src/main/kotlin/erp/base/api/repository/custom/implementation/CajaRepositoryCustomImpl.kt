package erp.base.api.repository.custom.implementation

import erp.base.api.models.Caja
import erp.base.api.repository.custom.CajaRepositoryCustom
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class CajaRepositoryCustomImpl(private val databaseClient: DatabaseClient) : CajaRepositoryCustom {

    override fun getBySearchParams(
        branchId: Int?,
        name: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<Caja> {
        var spec = databaseClient.sql(buildQueryForSearch(branchId, name, sortField, sortDirection, limit, offset, false))
        if (branchId != null) spec = spec.bind("branchId", branchId)
        if (name != null) spec = spec.bind("name", name)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    override fun getCountBySearchParams(branchId: Int?, name: String?): Mono<Long> {
        var spec = databaseClient.sql(buildQueryForSearch(branchId, name, null, null, null, null, true))
        if (branchId != null) spec = spec.bind("branchId", branchId)
        if (name != null) spec = spec.bind("name", name)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        branchId: Int?,
        name: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (isCount) "SELECT COUNT(*)" else "SELECT caj.id_caja as idCaja, caj.id_sucursal as idSucursal, caj.nombre_caja as nombreCaja, caj.estado_caja as estadoCaja")
        q.append(" FROM CAJA caj WHERE caj.estado_caja = true")
        if (branchId != null) q.append(" AND caj.id_sucursal = :branchId")
        if (name != null) q.append(" AND UPPER(caj.nombre_caja) LIKE UPPER(CONCAT('%',:name,'%'))")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): Caja = Caja(
        idCaja = row.get("idCaja", Int::class.javaObjectType),
        idSucursal = row.get("idSucursal", Int::class.javaObjectType),
        nombreCaja = row.get("nombreCaja", String::class.java),
        estadoCaja = row.get("estadoCaja", Boolean::class.javaObjectType)
    )
}
