package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table("sesion")
data class Sesion(
    @Id
    @Column("id_sesion")
    @JsonProperty("sessionId")
    var idSesion: Int? = null,

    @Column("id_usr")
    @JsonProperty("userId")
    var idUsr: Int? = null,

    @Column("token")
    var token: String? = null,

    @Column("fecha_creacion")
    @JsonProperty("creationDate")
    var fechaCreacion: ZonedDateTime? = null,

    @Column("fecha_expiracion")
    @JsonProperty("expirationDate")
    var fechaExpiracion: ZonedDateTime? = null,

    @Column("estado")
    @JsonProperty("state")
    var estado: Boolean? = null,

    @Column("duracion_minutos")
    @JsonProperty("durationMinutes")
    var duracionMinutos: Int? = null,
)
