package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class FormaPagoCobroDto(
    @JsonProperty("paymentCollectionMethodId")
    val idFormaPagoCobro: String? = null,
    @JsonProperty("paymentCollectionMethodName")
    val nombreFormaPagoCobro: String? = null,
    @JsonProperty("paymentCollectionMethodAlias")
    val aliasFormaPagoCobro: String? = null,
    @JsonProperty("paymentCollectionMethodDiscount")
    val descuentoFormaPagoCobro: BigDecimal? = null,
    @JsonProperty("paymentCollectionMethodCode")
    val codigoFormaPagoCobro: String? = null,
    @JsonProperty("paymentCollectionMethodStatus")
    val estadoFormaPagoCobro: Boolean? = null,
)
