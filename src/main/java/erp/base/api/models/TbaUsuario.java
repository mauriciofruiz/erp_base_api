package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("tba_usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaUsuario {
    @Id
    @Column("id_usr")
    @JsonProperty("userId")
    private Integer idUsr;

    @Column("id_sucursal")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("id_persona")
    @JsonProperty("personId")
    private Integer idPersona;

    @Column("nick_usr")
    @JsonProperty("nick")
    private String nickUsr;

    @Column("pass_usr")
    @JsonProperty("password")
    private String passUsr;

    @Column("cargo_usr")
    @JsonProperty("position")
    private Integer cargoUsr;

    @Column("fechaingreso_usr")
    @JsonProperty("entryDate")
    private LocalDateTime fechaIngresoUsr;

    @Column("fechainactivacion_usr")
    @JsonProperty("deactivationDate")
    private LocalDateTime fechaInactivacionUsr;

    @Column("horainicio_usr")
    @JsonProperty("startTime")
    private String horaInicioUsr;

    @Column("horafin_usr")
    @JsonProperty("endTime")
    private String horaFinUsr;

    @Column("tiempo_usr")
    @JsonProperty("time")
    private Integer tiempoUsr;

    @Column("coddesbloqueo_usr")
    @JsonProperty("unlockCode")
    private String codigoDesbloqueoUsr;

    @Column("img_usr")
    @JsonProperty("photo")
    private String imgUsr;

    @Column("estado_usr")
    @JsonProperty("state")
    private Boolean estadoUsr;

    @Column("id_caja")
    @JsonProperty("cashId")
    private Integer idCaja;
}
