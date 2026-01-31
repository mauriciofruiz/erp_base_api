package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CUPON")
data class Cupon(
    @Id
    @Column("ID_CUPON")
    @JsonProperty("couponId")
    val idCupon: Int,
    @Column("ANULADO_CUPON")
    @JsonProperty("isCancelled")
    val anulado: Boolean? = null,
    @Column("ENVIADOCORREO_CUPON")
    @JsonProperty("isEmailSent")
    val enviadoCorreo: Boolean? = null,
    @Column("NUM_CUPON")
    @JsonProperty("couponNumber")
    val numCupon: Int? = null,
    @Column("ID_FACTURA_CUPON")
    @JsonProperty("invoiceId")
    val idFacturaCupon: Int? = null,
    @Column("SORTEOACTIVO_CUPON")
    @JsonProperty("isLotteryActive")
    val sorteoActivo: Boolean? = null,
)
