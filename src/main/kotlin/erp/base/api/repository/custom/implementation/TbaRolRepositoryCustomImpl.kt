package erp.base.api.repository.custom.implementation

import erp.base.api.models.TbaRol
import erp.base.api.repository.custom.TbaRolRepositoryCustom
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class TbaRolRepositoryCustomImpl(private val databaseClient: DatabaseClient) : TbaRolRepositoryCustom {

    override fun getBySearchParams(
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<TbaRol> {
        var spec = databaseClient.sql(buildQueryForSearch(sortField, sortDirection, limit, offset, false))
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    override fun getCountBySearchParams(): Mono<Long> {
        return databaseClient.sql(buildQueryForSearch(null, null, null, null, true))
            .map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (isCount) "SELECT COUNT(*)" else "SELECT rol.id_rol as idRol, rol.nombre_rol as nameRol, rol.descripcion_rol as descriptionRol")
        q.append(" FROM tba_rol rol")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): TbaRol = TbaRol(
        idRol = row.get("idRol", Int::class.javaObjectType),
        nombreRol = row.get("nameRol", String::class.java),
        descripcionRol = row.get("descriptionRol", String::class.java)
    )
}
