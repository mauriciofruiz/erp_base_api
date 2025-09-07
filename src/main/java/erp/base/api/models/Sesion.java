package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Table("sesion")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Sesion {

    @Id
    @Column("id_sesion")
    @JsonProperty("sessionId")
    private Integer idSesion;

    @Column("id_usr")
    @JsonProperty("userId")
    private Integer idUsr;

    @Column("token")
    private String token;

    @Column("fecha_creacion")
    @JsonProperty("creationDate")
    private ZonedDateTime fechaCreacion;

    @Column("fecha_expiracion")
    @JsonProperty("expirationDate")
    private ZonedDateTime fechaExpiracion;

    @Column("estado")
    @JsonProperty("state")
    private Boolean estado;

    @Column("duracion_minutos")
    @JsonProperty("durationMinutes")
    private Integer duracionMinutos;
}
