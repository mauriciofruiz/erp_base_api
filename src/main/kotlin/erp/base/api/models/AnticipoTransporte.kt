package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate

@Table("ANTICIPOTRANSPORTE")
data class AnticipoTransporte(
    @Id
    @Column("ID_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceId")
    val idAnticipoTransporte: Int,
    @Column("ID_FACTURA")
    @JsonProperty("invoiceId")
    val idFactura: Int? = null,
    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    val idAsiento: Int? = null,
    @Column("ID_DETCOBRO")
    @JsonProperty("paymentDetailId")
    val idDetCobro: Int? = null,
    @Column("DESC_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceDescription")
    val descAnticipoTransporte: String? = null,
    @Column("VAL_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceValue")
    val valAnticipoTransporte: BigDecimal? = null,
    @Column("LIQUIDADO_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceLiquidated")
    val liquidadoAnticipoTransporte: Boolean? = null,
    @Column("ANULADO_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceCancelled")
    val anuladoAnticipoTransporte: Boolean? = null,
    @Column("FECHA_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceDate")
    val fechaAnticipoTransporte: LocalDate? = null,
    @Column("CODSUCURSAL_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceBranchCode")
    val codSucursalAnticipoTransporte: String? = null,
)
