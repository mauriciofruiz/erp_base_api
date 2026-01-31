package erp.base.api.repository.custom.implementation

import erp.base.api.models.Banco
import erp.base.api.repository.custom.BancoRepositoryCustom
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class BancoRepositoryCustomImpl(private val databaseClient: DatabaseClient) : BancoRepositoryCustom {

    override fun getBySearchParams(
        nameBank: String?,
        numCount: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<Banco> {
        var spec = databaseClient.sql(buildQueryForSearch(nameBank, numCount, sortField, sortDirection, limit, offset, false))
        if (nameBank != null) spec = spec.bind("nameBank", nameBank)
        if (numCount != null) spec = spec.bind("numCount", numCount)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    override fun getCountBySearchParams(nameBank: String?, numCount: String?): Mono<Long> {
        var spec = databaseClient.sql(buildQueryForSearch(nameBank, numCount, null, null, null, null, true))
        if (nameBank != null) spec = spec.bind("nameBank", nameBank)
        if (numCount != null) spec = spec.bind("numCount", numCount)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        nameBank: String?,
        numCount: String?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (isCount) "SELECT COUNT(*)" else "SELECT banc.id_banco as idBanco, banc.nombre_banco as nombreBanco, banc.numerocuenta_banco as cuenta")
        q.append(" FROM tba_banco banc WHERE banc.estado_banco = true")
        if (nameBank != null) q.append(" AND banc.nombre_banco LIKE CONCAT('%', :nameBank, '%')")
        if (numCount != null) q.append(" AND banc.numerocuenta_banco LIKE CONCAT('%', :numCount, '%')")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): Banco = Banco(
        idBanco = row.get("idBanco", Int::class.javaObjectType),
        nombreBanco = row.get("nombreBanco", String::class.java),
        numeroCuentaBanco = row.get("cuenta", String::class.java)
    )
}
