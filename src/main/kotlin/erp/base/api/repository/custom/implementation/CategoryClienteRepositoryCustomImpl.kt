package erp.base.api.repository.custom.implementation

import erp.base.api.models.CategoriaCliente
import erp.base.api.repository.custom.CategoryClienteRepositoryCustom
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Repository
class CategoryClienteRepositoryCustomImpl(private val databaseClient: DatabaseClient) : CategoryClienteRepositoryCustom {

    override fun getBySearchParams(
        name: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<CategoriaCliente> {
        var spec = databaseClient.sql(buildQueryForSearch(name, sortField, sortDirection, limit, offset, false))
        if (name != null) spec = spec.bind("name", name)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    override fun getCountBySearchParams(name: String?): Mono<Long> {
        var spec = databaseClient.sql(buildQueryForSearch(name, null, null, null, null, true))
        if (name != null) spec = spec.bind("name", name)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        name: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (isCount) "SELECT COUNT(*)" else "SELECT *")
        q.append(" FROM CATEGORIACLIENTE catCli WHERE catCli.activocategoria_categoriacliente = true")
        if (name != null) q.append(" AND UPPER(catCli.nombre_categoriacliente) LIKE UPPER(CONCAT('%',:name,'%'))")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): CategoriaCliente = CategoriaCliente(
        idCategoriaCliente = row.get("id_categoriacliente", Int::class.javaObjectType),
        nombreCategoriaCliente = row.get("nombre_categoriacliente", String::class.java),
        descuentoCategoriaCliente = row.get("descuento_categoriacliente", BigDecimal::class.java),
        categoriaEspecialCategoriaCliente = row.get("categoriaespecial_categoriacliente", Boolean::class.javaObjectType),
        activoCategoriaCliente = row.get("activocategoria_categoriacliente", Boolean::class.javaObjectType)
    )
}
