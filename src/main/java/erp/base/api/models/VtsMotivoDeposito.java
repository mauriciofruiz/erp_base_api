package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("MOTIVODEPOSITO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VtsMotivoDeposito {
    @Id
    @Column("ID_MOTIVO")
    @JsonProperty("reasonId")
    private Integer idMotivo;

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("NOMBRE_MOTIVO")
    @JsonProperty("reasonName")
    private String nombreMotivo;

    @Column("VALOR_MOTIVO")
    @JsonProperty("reasonValue")
    private BigDecimal valorMotivo;

    @Column("VALORPENDIENTE_MOTIVO")
    @JsonProperty("reasonPendingValue")
    private BigDecimal valorPendienteMotivo;

    @Column("ASIGNADO_MOTIVO")
    @JsonProperty("reasonAssigned")
    private Boolean asignadoMotivo;

    @Column("FECHA_MOTIVO")
    @JsonProperty("reasonDate")
    private LocalDate fechaMotivo;

    @Column("CODAPERTURA_MOTIVO")
    @JsonProperty("reasonOpeningCode")
    private String codigoAperturaMotivo;

    @Column("CODCAJA_MOTIVO")
    @JsonProperty("reasonBoxCode")
    private String codigoCajaMotivo;

    @Column("CODDETCOBRO_MOTIVO")
    @JsonProperty("reasonPaymentDetailCode")
    private String codigoDetCobroMotivo;

    @Column("ESTADO_MOTIVO")
    @JsonProperty("reasonState")
    private Boolean estadoMotivo;

    @Column("CODCOBRO_MOTIVO")
    @JsonProperty("reasonPaymentCode")
    private Integer codigoCobroMotivo;

    @Column("CODCLIENTE_MOTIVO")
    @JsonProperty("reasonClientCode")
    private Integer codigoClienteMotivo;
}
