package erp.base.api.repository.custom.implementation

import erp.base.api.models.BancoCatalogo
import erp.base.api.repository.custom.BancoCatalogoRespositoryCustom
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class BancoCatalogoRespositoryCustomImpl(private val databaseClient: DatabaseClient) : BancoCatalogoRespositoryCustom {

    override fun getBySearchParams(
        bankCatalogName: String?,
        bankCatalogCode: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<BancoCatalogo> {
        var spec = databaseClient.sql(buildQueryForSearch(bankCatalogName, bankCatalogCode, sortField, sortDirection, limit, offset, false))
        if (bankCatalogName != null) spec = spec.bind("bankCatalogName", bankCatalogName)
        if (bankCatalogCode != null) spec = spec.bind("bankCatalogCode", bankCatalogCode)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    override fun getCountBySearchParams(bankCatalogName: String?, bankCatalogCode: String?): Mono<Long> {
        var spec = databaseClient.sql(buildQueryForSearch(bankCatalogName, bankCatalogCode, null, null, null, null, true))
        if (bankCatalogName != null) spec = spec.bind("bankCatalogName", bankCatalogName)
        if (bankCatalogCode != null) spec = spec.bind("bankCatalogCode", bankCatalogCode)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        bankCatalogName: String?,
        bankCatalogCode: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (isCount) "SELECT COUNT(*)" else "SELECT cat.id_bancocatalogo as idBancoCat, cat.nombre_bancocatalogo as nombreCatBanco, cat.codigo_bancocatalogo as codigoCat")
        q.append(" FROM tba_bancocatalogo cat WHERE cat.estado_banco = true")
        if (bankCatalogName != null) q.append(" AND cat.nombre_bancocatalogo LIKE CONCAT('%', :bankCatalogName, '%')")
        if (bankCatalogCode != null) q.append(" AND cat.codigo_bancocatalogo LIKE CONCAT('%', :bankCatalogCode, '%')")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): BancoCatalogo = BancoCatalogo(
        idBancoCatalogo = row.get("idBancoCat", Int::class.java),
        nombreBancoCatalogo = row.get("nombreCatBanco", String::class.java),
        codigoBancoCatalogo = row.get("codigoCat", String::class.java)
    )
}
