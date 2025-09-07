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

@Table("VTS_ANTICIPOTRANSPORTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VtsAnticipoTransporte {
    @Id
    @Column("ID_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceId")
    private Integer idAnticipoTransporte;

    @Column("ID_FACTURA")
    @JsonProperty("invoiceId")
    private Integer idFactura;

    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    private Integer idAsiento;

    @Column("ID_DETCOBRO")
    @JsonProperty("paymentDetailId")
    private Integer idDetCobro;

    @Column("DESC_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceDescription")
    private String descAnticipoTransporte;

    @Column("VAL_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceValue")
    private BigDecimal valAnticipoTransporte;

    @Column("LIQUIDADO_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceLiquidated")
    private Boolean liquidadoAnticipoTransporte;

    @Column("ANULADO_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceCancelled")
    private Boolean anuladoAnticipoTransporte;

    @Column("FECHA_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceDate")
    private LocalDate fechaAnticipoTransporte;

    @Column("CODSUCURSAL_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceBranchCode")
    private String codSucursalAnticipoTransporte;
}
