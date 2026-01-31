package erp.base.api.repository.custom

import erp.base.api.dto.MenuDto
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class MenuCustom(private val databaseClient: DatabaseClient) {

    fun getBySearchParams(
        menuId: Int?,
        menuName: String?,
        menuLevel: Int?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<MenuDto> {
        var spec = databaseClient.sql(buildQueryForSearch(menuId, menuName, menuLevel, sortField, sortDirection, limit, offset, false))
        if (menuId != null) spec = spec.bind("menuId", menuId)
        if (menuName != null) spec = spec.bind("menuName", menuName)
        if (menuLevel != null) spec = spec.bind("menuLevel", menuLevel)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    fun getCountBySearchParams(menuId: Int?, menuName: String?, menuLevel: Int?): Mono<Long> {
        var spec = databaseClient.sql(buildQueryForSearch(menuId, menuName, menuLevel, null, null, null, null, true))
        if (menuId != null) spec = spec.bind("menuId", menuId)
        if (menuName != null) spec = spec.bind("menuName", menuName)
        if (menuLevel != null) spec = spec.bind("menuLevel", menuLevel)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        menuId: Int?,
        menuName: String?,
        menuLevel: Int?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (isCount) "SELECT COUNT(*)" else "SELECT id_menu as menuId, nombre_menu as menuName, padre_menu as menuFather, nivel_menu as menuLevel, icon_menu as menuIcon, component_menu as menuComponent, url_menu as menuUrl")
        q.append(" FROM tba_menu")
        if (menuId != null) q.append(" WHERE id_menu = :menuId")
        if (menuName != null) q.append(" WHERE UPPER(nombre_menu) LIKE UPPER(CONCAT('%',:menuName,'%'))")
        if (menuLevel != null) q.append(" WHERE nivel_menu = :menuLevel")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): MenuDto = MenuDto(
        idMenu = row.get("menuId", Int::class.javaObjectType),
        nombreMenu = row.get("menuName", String::class.java),
        padreMenu = row.get("menuFather", Int::class.javaObjectType),
        nivelMenu = row.get("menuLevel", Int::class.javaObjectType),
        iconMenu = row.get("menuIcon", String::class.java),
        menuComponent = row.get("menuComponent", String::class.java),
        urlMenu = row.get("menuUrl", String::class.java)
    )
}
