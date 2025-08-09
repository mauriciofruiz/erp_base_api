package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("TBA_SOLICITUD")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaSolicitud {
    @Id
    @Column("ID_SOLICITUD")
    @JsonProperty("requestId")
    private Integer idSolicitud;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("ID_ESTADO")
    @JsonProperty("stateId")
    private Integer idEstado;

    @Column("ASUNTO_SOLICITUD")
    @JsonProperty("subject")
    private String asuntoSolicitud;

    @Column("USUARIORECIBE_SOLICITUD")
    @JsonProperty("receiverUserId")
    private Integer usuarioRecibeSolicitud;

    @Column("DESCRIPCION_SOLICITUD")
    @JsonProperty("description")
    private String descripcionSolicitud;

    @Column("FECHA_SOLICITUD")
    @JsonProperty("requestDate")
    private LocalDateTime fechaSolicitud;

    @Column("ADJUNTO_SOLICITUD")
    @JsonProperty("attachment")
    private byte[] adjuntoSolicitud;

    @Column("FECHARESPUESTA_SOLICITUD")
    @JsonProperty("responseDate")
    private LocalDateTime fechaRespuestaSolicitud;
}
