package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Table("DETCOBRO")
data class DetCobro(
    @Id
    @Column("ID_DETCOBRO")
    @JsonProperty("paymentDetailId")
    val idDetCobro: Int,
    @Column("ID_FACTURA")
    @JsonProperty("invoiceId")
    val idFactura: Int? = null,
    @Column("ID_TARJETA")
    @JsonProperty("cardId")
    val idTarjeta: Int? = null,
    @Column("ID_ADQUIRIENTE")
    @JsonProperty("acquirerId")
    val idAdquiriente: Int? = null,
    @Column("ID_BANCOCATALOGO")
    @JsonProperty("bankCatalogId")
    val idBancoCatalogo: Int? = null,
    @Column("ID_FORMAPAGOCOBRO")
    @JsonProperty("paymentMethodId")
    val idFormaPagoCobro: String? = null,
    @Column("ID_COBRO")
    @JsonProperty("paymentId")
    val idCobro: Int? = null,
    @Column("VALOR_DETCOBRO")
    @JsonProperty("paymentAmount")
    val valorDetCobro: BigDecimal? = null,
    @Column("NUMERODOCUMENTO_DETCOBRO")
    @JsonProperty("documentNumber")
    val numeroDocumentoDetCobro: String? = null,
    @Column("FECHATRANSACCION_DETCOBRO")
    @JsonProperty("transactionDate")
    val fechaTransaccionDetCobro: LocalDateTime? = null,
    @Column("FECHACOBRO_DETCOBRO")
    @JsonProperty("paymentDate")
    val fechaCobroDetCobro: LocalDate? = null,
    @Column("NUMEROTC_DETCOBRO")
    @JsonProperty("cardNumber")
    val numeroTcDetCobro: String? = null,
    @Column("LOTETARJETA_DETCOBRO")
    @JsonProperty("cardBatch")
    val loteTarjetaDetCobro: String? = null,
    @Column("MESESDIFERIDO_DETCOBRO")
    @JsonProperty("installmentMonths")
    val mesesDiferidoDetCobro: Int? = null,
    @Column("NUMCUOTA_DETCOBRO")
    @JsonProperty("installmentNumber")
    val numeroCuotaDetCobro: Int? = null,
    @Column("OBS_DETCOBRO")
    @JsonProperty("observation")
    val observacionDetCobro: String? = null,
    @Column("VALORANTICIPO_DETCOBRO")
    @JsonProperty("advanceAmount")
    val valorAnticipoDetCobro: BigDecimal? = null,
    @Column("VALORTRANS_DETCOBRO")
    @JsonProperty("transactionAmount")
    val valorTransDetCobro: BigDecimal? = null,
    @Column("CODNC_DETCOBRO")
    @JsonProperty("ncCode")
    val codNcDetCobro: BigDecimal? = null,
    @Column("ESTADO_DETCOBRO")
    @JsonProperty("status")
    val estadoDetCobro: Boolean? = null,
    @Column("ID_FACTURACUOTA")
    @JsonProperty("installmentInvoiceId")
    val idFacturaCuota: Int? = null,
)
