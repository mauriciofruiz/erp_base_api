package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("ROL")
data class TbaRol(
    @Id
    @Column("ID_ROL")
    @JsonProperty("roleId")
    var idRol: Int? = null,

    @Column("NOMBRE_ROL")
    @JsonProperty("roleName")
    var nombreRol: String? = null,

    @Column("DESCRIPCION_ROL")
    @JsonProperty("roleDescription")
    var descripcionRol: String? = null,

    @Column("HORAINICIO_ROL")
    @JsonProperty("startTime")
    var horaInicioRol: String? = null,

    @Column("HORAFIN_ROL")
    @JsonProperty("endTime")
    var horaFinRol: String? = null,

    @Column("TIEMPO_ROL")
    @JsonProperty("roleTime")
    var tiempoRol: Int? = null,

    @Column("id_usuario_creacion")
    @JsonProperty("creationUserId")
    var idUsuarioCreacion: Int? = null,

    @Column("id_usuario_modificacion")
    @JsonProperty("modificationUserId")
    var idUsuarioModificacion: Int? = null,

    @Column("fecha_creacion")
    @JsonProperty("registrationDate")
    var fechaCreacion: LocalDateTime? = null,

    @Column("fecha_modificacion")
    @JsonProperty("modificationDate")
    var fechaModificacion: LocalDateTime? = null,
)
