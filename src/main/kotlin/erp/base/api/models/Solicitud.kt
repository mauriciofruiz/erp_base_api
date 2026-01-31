package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("SOLICITUD")
data class Solicitud(
    @Id
    @Column("ID_SOLICITUD")
    @JsonProperty("requestId")
    val idSolicitud: Int,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @Column("ID_ESTADO")
    @JsonProperty("stateId")
    val idEstado: Int? = null,
    @Column("ASUNTO_SOLICITUD")
    @JsonProperty("subject")
    val asuntoSolicitud: String? = null,
    @Column("USUARIORECIBE_SOLICITUD")
    @JsonProperty("receiverUserId")
    val usuarioRecibeSolicitud: Int? = null,
    @Column("DESCRIPCION_SOLICITUD")
    @JsonProperty("description")
    val descripcionSolicitud: String? = null,
    @Column("FECHA_SOLICITUD")
    @JsonProperty("requestDate")
    val fechaSolicitud: LocalDateTime? = null,
    @Column("ADJUNTO_SOLICITUD")
    @JsonProperty("attachment")
    val adjuntoSolicitud: ByteArray? = null,
    @Column("FECHARESPUESTA_SOLICITUD")
    @JsonProperty("responseDate")
    val fechaRespuestaSolicitud: LocalDateTime? = null,
)
