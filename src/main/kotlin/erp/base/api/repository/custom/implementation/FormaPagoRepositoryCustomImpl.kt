package erp.base.api.repository.custom.implementation

import erp.base.api.models.FormaPagoCobro
import erp.base.api.repository.custom.FormaPagoRepositoryCustom
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Repository
class FormaPagoRepositoryCustomImpl(private val databaseClient: DatabaseClient) : FormaPagoRepositoryCustom {

    override fun getBySearchParams(
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<FormaPagoCobro> {
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
        val q = StringBuilder(if (isCount) "SELECT COUNT(*)" else "SELECT *")
        q.append(" FROM FORMAPAGOCOBRO")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): FormaPagoCobro = FormaPagoCobro(
        idFormaPagoCobro = row.get("id_formapagocobro", String::class.java),
        nombreFormaPagoCobro = row.get("nombre_formapagocobro", String::class.java),
        aliasFormaPagoCobro = row.get("alias_formapagocobro", String::class.java),
        descuentoFormaPagoCobro = row.get("descuento_formapagocobro", BigDecimal::class.java),
        codigoFormaPagoCobro = row.get("cod_formapagocobro", String::class.java),
        estadoFormaPagoCobro = row.get("estado_formapagocobro", Boolean::class.javaObjectType)
    )
}
