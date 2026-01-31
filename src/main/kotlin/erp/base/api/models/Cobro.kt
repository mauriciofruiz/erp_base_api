package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("COBRO")
data class Cobro(
    @Id
    @Column("ID_COBRO")
    @JsonProperty("paymentId")
    val idCobro: Int,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    val idAsiento: Int? = null,
    @Column("ID_CAJA")
    @JsonProperty("cashboxId")
    val idCaja: Int? = null,
    @Column("ID_CLIENTE")
    @JsonProperty("clientId")
    val idCliente: Int? = null,
    @Column("ID_APERTURACAJA")
    @JsonProperty("cashOpeningId")
    val idAperturaCaja: Int? = null,
    @Column("MONTO_COBRO")
    @JsonProperty("amount")
    val montoCobro: BigDecimal? = null,
    @Column("FECHA_COBRO")
    @JsonProperty("paymentDate")
    val fechaCobro: LocalDateTime? = null,
    @Column("FORMAPAGO_COBRO")
    @JsonProperty("paymentMethod")
    val formaPagoCobro: String? = null,
    @Column("ANTICIPO_COBRO")
    @JsonProperty("advancePayment")
    val anticipoCobro: Boolean? = null,
    @Column("ANULADO_COBRO")
    @JsonProperty("cancelled")
    val anuladoCobro: Boolean? = null,
    @Column("ANTICIPOTRANSPORTE_COBRO")
    @JsonProperty("transportAdvancePayment")
    val anticipotransporteCobro: Boolean? = null,
    @Column("CODNC_COBRO")
    @JsonProperty("ncCode")
    val codNcCobro: String? = null,
    @Column("ESRECUPERACION_COBRO")
    @JsonProperty("recoveryPayment")
    val esRecuperacionCobro: Boolean? = null,
    @Column("MOTIVOANULACION_COBRO")
    @JsonProperty("cancellationReason")
    val motivoAnulacionCobro: String? = null,
    @Column("FECHAEDICIONLOTE_COBRO")
    @JsonProperty("batchEditionDate")
    val fechaEdicionLoteCobro: LocalDateTime? = null,
    @Column("USUARIOEDITALOTE_COBRO")
    @JsonProperty("batchEditionUser")
    val usuarioEdicionLoteCobro: String? = null,
)
