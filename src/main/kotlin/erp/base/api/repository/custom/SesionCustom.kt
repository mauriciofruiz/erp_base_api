package erp.base.api.repository.custom

import erp.base.api.dto.SesionDto
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class SesionCustom(private val databaseClient: DatabaseClient) {

    fun getBySearchParams(
        userId: Int?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<SesionDto> {
        var spec = databaseClient.sql(buildQueryForSearch(userId, sortField, sortDirection, limit, offset, false))
        if (userId != null) spec = spec.bind("userId", userId)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    fun getCountBySearchParams(userId: Int?): Mono<Long> {
        var spec = databaseClient.sql(buildQueryForSearch(userId, null, null, null, null, true))
        if (userId != null) spec = spec.bind("userId", userId)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        userId: Int?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (!isCount) "SELECT s.id_sesion as sessionId, s.id_usr as userId, u.nick_usr as nickName, p.nombre_persona as personName, s.estado as estado" else "SELECT COUNT(*)")
        q.append(" FROM sesion s JOIN tba_usuario u ON s.id_usr = u.id_usr JOIN tba_persona p ON p.id_persona = u.id_persona")
        q.append(" WHERE s.estado = true")
        if (userId != null) q.append(" AND s.id_usr = :userId")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortDirection).append(" ").append(sortField)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): SesionDto = SesionDto(
        idSesion = row.get("sessionId", Int::class.javaObjectType),
        idUsr = row.get("userId", Int::class.javaObjectType),
        nick_usr = row.get("nickName", String::class.java),
        nombre_persona = row.get("personName", String::class.java),
        estado = row.get("estado", Boolean::class.javaObjectType)
    )
}
