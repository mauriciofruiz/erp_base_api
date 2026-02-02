package erp.base.api.repository.custom

import erp.base.api.models.Usuario
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.time.LocalDateTime

@Component
class UsuarioCustom(private val databaseClient: DatabaseClient) {

    fun getUserByRoleId(roleId: Int): Flux<Usuario> {
        var spec = databaseClient.sql(buildQueryForSearch()).bind("roleId", roleId)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    private fun buildQueryForSearch(): String =
        "SELECT usu.* FROM ROLUSUARIO rolusu JOIN tba_usuario usu ON rolusu.ID_USR = usu.ID_USR WHERE rolusu.ID_ROL = :roleId"

    private fun mapRowToEntity(row: Row): Usuario = Usuario(
        idUsr = row.get("id_usr", Int::class.javaObjectType),
        idSucursal = row.get("id_sucursal", Int::class.javaObjectType),
        idPersona = row.get("id_persona", Int::class.javaObjectType),
        nickUsr = row.get("nick_usr", String::class.java),
        passUsr = row.get("pass_usr", String::class.java),
        fechaIngresoUsr = row.get("fechaingreso_usr", LocalDateTime::class.java),
        fechaInactivacionUsr = row.get("fechainactivacion_usr", LocalDateTime::class.java),
        horaInicioUsr = row.get("horainicio_usr", String::class.java),
        horaFinUsr = row.get("horafin_usr", String::class.java),
        tiempoUsr = row.get("tiempo_usr", Int::class.javaObjectType),
        codigoDesbloqueoUsr = row.get("coddesbloqueo_usr", String::class.java),
        imgUsr = row.get("img_usr", String::class.java),
        estadoUsr = row.get("estado_usr", Boolean::class.javaObjectType),
        idCaja = row.get("id_caja", Int::class.javaObjectType)
    )
}
