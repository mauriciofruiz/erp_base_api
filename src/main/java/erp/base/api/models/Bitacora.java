package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Table("BITACORA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Bitacora {
    @Id
    @Column("ID_BITACORA")
    @JsonProperty("logId")
    private Integer idBitacora;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @NotNull(message = "El identificador es requerido")
    @Column("IDENTIFICADOR_BITACORA")
    @JsonProperty("identifier")
    private Integer identificador;

    @Column("TABLE_BITACORA")
    @JsonProperty("table")
    private String tabla;

    @Column("CAMPO_BITACORA")
    @JsonProperty("field")
    private String campo;

    @Column("OLDVAL_BITACORA")
    @JsonProperty("oldValue")
    private String oldValue;

    @Column("NEWVAL_BITACORA")
    @JsonProperty("newValue")
    private String newValue;

    @Column("FECHA_BITACORA")
    @JsonProperty("date")
    private ZonedDateTime fecha;

    @Column("IPDISPOSITIVO_BITACORA")
    @JsonProperty("deviceIp")
    private String ipDispositivo;

    @Column("NOMBREDISPOSITIVO_BITACORA")
    @JsonProperty("deviceName")
    private String nombreDispositivo;
}
