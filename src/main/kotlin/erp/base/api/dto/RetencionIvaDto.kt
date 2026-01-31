package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class RetencionIvaDto(
    @JsonProperty("accountId")
    val idCuenta: Int? = null,
    @JsonProperty("retentionIvaCode")
    val codigoRetencionIva: String? = null,
    @JsonProperty("retentionIvaDescription")
    val descripcionRetencionIva: String? = null,
    @JsonProperty("retentionIvaPercentage")
    val porcentajeRetencionIva: BigDecimal? = null,
    @JsonProperty("canceledRetentionIva")
    val anuladoRetencionIva: Boolean? = null,
)
