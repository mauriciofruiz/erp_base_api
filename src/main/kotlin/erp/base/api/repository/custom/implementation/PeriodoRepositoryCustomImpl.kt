package erp.base.api.repository.custom.implementation

import erp.base.api.models.Periodo
import erp.base.api.repository.custom.PeriodoRepositoryCustom
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.ZonedDateTime

@Repository
class PeriodoRepositoryCustomImpl(private val databaseClient: DatabaseClient) : PeriodoRepositoryCustom {

    override fun getBySearchParams(
        moduleId: Int?,
        startDate: ZonedDateTime?,
        endDate: ZonedDateTime?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<Periodo> {
        var spec = databaseClient.sql(buildQueryForSearch(moduleId, sortField, sortDirection, limit, offset, false))
            .bind("startDate", startDate)
            .bind("endDate", endDate)
        if (moduleId != null) spec = spec.bind("moduleId", moduleId)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    override fun getCountBySearchParams(
        moduleId: Int?,
        startDate: ZonedDateTime?,
        endDate: ZonedDateTime?
    ): Mono<Long> {
        var spec = databaseClient.sql(buildQueryForSearch(moduleId, null, null, null, null, true))
            .bind("startDate", startDate)
            .bind("endDate", endDate)
        if (moduleId != null) spec = spec.bind("moduleId", moduleId)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        moduleId: Int?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (isCount) "SELECT COUNT(*)" else "SELECT per.id_periodo as idPeriodo, per.fechacierre_periodo as periodoCierre, per.fechaanterior_periodo as periodoAnterior, per.id_modulo as idModulo")
        q.append(" FROM con_periodo per WHERE per.fechacierre_periodo BETWEEN :startDate AND :endDate")
        if (moduleId != null) q.append(" AND per.id_modulo = :moduleId")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): Periodo = Periodo(
        idPeriodo = row.get("idPeriodo", Int::class.javaObjectType),
        fechaCierrePeriodo = row.get("periodoCierre", LocalDate::class.java),
        fechaAnteriorPeriodo = row.get("periodoAnterior", LocalDate::class.java),
        idModulo = row.get("idModulo", Int::class.javaObjectType)
    )
}
