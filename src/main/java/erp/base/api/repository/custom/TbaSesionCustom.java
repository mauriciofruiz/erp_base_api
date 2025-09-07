package erp.base.api.repository.custom;

import erp.base.api.dto.SesionDto;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TbaSesionCustom {
    private final DatabaseClient databaseClient;

    public Flux<SesionDto> getBySearchParams(
            Integer userId,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(userId, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    public Mono<Long> getCountBySearchParams(
            Integer userId
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(userId,null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private String buildQueryForSearch(
            Integer userId,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        StringBuilder queryBuilder = new StringBuilder(!isCount ? "SELECT s.id_sesion as sessionId, s.id_usr as userId, u.nick_usr as nickName, p.nombre_persona as personName, s.estado as estado" : "SELECT COUNT(*)");
        queryBuilder.append(" FROM sesion s");
        queryBuilder.append(" JOIN tba_usuario u ON s.id_usr = u.id_usr");
        queryBuilder.append(" JOIN tba_persona p ON p.id_persona = u.id_persona");
        queryBuilder.append(" WHERE s.estado  = true");
        if (userId != null)
            queryBuilder.append(" AND s.id_usr = :userId");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortDirection).append(" ").append(sortField);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");
        return queryBuilder.toString();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            Integer userId,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(userId, sortField, sortDirection, limit, offset, isCount));
        if (userId != null)
            spec = spec.bind("userId", userId);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private SesionDto mapRowToEntity(Row row) {
        return SesionDto.builder()
                .idSesion(row.get("sessionId", Integer.class))
                .idUsr(row.get("userId", Integer.class))
                .nick_usr(row.get("nickName", String.class))
                .nombre_persona(row.get("personName", String.class))
                .estado(row.get("estado", Boolean.class))
                .build();
    }
}
