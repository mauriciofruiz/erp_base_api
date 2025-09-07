package erp.base.api.repository.custom;

import erp.base.api.models.TbaUsuario;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TbaUsuarioCustom {

    private final DatabaseClient databaseClient;

    public Flux<TbaUsuario> getUserByRoleId(Integer roleId) {

        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch());
        spec = spec.bind("roleId", roleId);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    private String buildQueryForSearch() {
        return "SELECT usu.*" +
                " FROM TBA_ROLUSUARIO rolusu" +
                " JOIN tba_usuario usu ON rolusu.ID_USR = usu.ID_USR" +
                " WHERE rolusu.ID_ROL = :roleId";
    }

    private TbaUsuario mapRowToEntity(Row row) {
        return TbaUsuario.builder()
                .idUsr(row.get("id_usr", Integer.class))
                .idSucursal(row.get("id_sucursal", Integer.class))
                .idPersona(row.get("id_persona", Integer.class))
                .nickUsr(row.get("nick_usr", String.class))
                .passUsr(row.get("pass_usr", String.class))
                .cargoUsr(row.get("cargo_usr", Integer.class))
                .fechaIngresoUsr(row.get("fechaingreso_usr", LocalDateTime.class))
                .fechaInactivacionUsr(row.get("fechainactivacion_usr", LocalDateTime.class))
                .horaInicioUsr(row.get("horainicio_usr", String.class))
                .horaFinUsr(row.get("horafin_usr", String.class))
                .tiempoUsr(row.get("tiempo_usr", Integer.class))
                .codigoDesbloqueoUsr(row.get("coddesbloqueo_usr", String.class))
                .imgUsr(row.get("img_usr", String.class))
                .estadoUsr(row.get("estado_usr", Boolean.class))
                .idCaja(row.get("id_caja", Integer.class))
                .build();
    }
}
