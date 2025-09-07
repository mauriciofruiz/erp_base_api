package erp.base.api.repository.custom;

import erp.base.api.dto.TbaMenuDto;
import erp.base.api.models.TbaRol;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TbaMenuCustom {
    private final DatabaseClient databaseClient;

    public Flux<TbaMenuDto> getBySearchParams(
            Integer menuId,
            String menuName,
            Integer menuLevel,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(menuId, menuName, menuLevel, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    public Mono<Long> getCountBySearchParams(
            Integer menuId,
            String menuName,
            Integer menuLevel
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(menuId, menuName, menuLevel, null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            Integer menuId,
            String menuName,
            Integer menuLevel,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(menuId, menuName, menuLevel, sortField, sortDirection, limit, offset, isCount));
        if (menuId != null)
            spec = spec.bind("menuId", menuId);
        if (menuName != null)
            spec = spec.bind("menuName", menuName);
        if (menuLevel != null)
            spec = spec.bind("menuLevel", menuLevel);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private String buildQueryForSearch(
            Integer menuId,
            String menuName,
            Integer menuLevel,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT id_menu as menuId, nombre_menu as menuName, padre_menu as menuFather, nivel_menu as menuLevel, icon_menu as menuIcon, component_menu as menuComponent, url_menu as menuUrl");
        queryBuilder.append(" FROM tba_menu");
        if (menuId != null)
            queryBuilder.append(" WHERE id_menu = :menuId");
        if (menuName != null)
            queryBuilder.append(" WHERE UPPER(nombre_menu) LIKE UPPER(CONCAT('%',:menuName,'%'))");
        if (menuLevel != null)
            queryBuilder.append(" WHERE nivel_menu = :menuLevel");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private TbaMenuDto mapRowToEntity(Row row) {
        return TbaMenuDto.builder()
                .idMenu(row.get("menuId", Integer.class))
                .nombreMenu(row.get("menuName", String.class))
                .padreMenu(row.get("menuFather", Integer.class))
                .nivelMenu(row.get("menuLevel", Integer.class))
                .iconMenu(row.get("menuIcon", String.class))
                .menuComponent(row.get("menuComponent", String.class))
                .urlMenu(row.get("menuUrl", String.class))
                .build();
    }
}
