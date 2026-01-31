package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class TarifaIvaDto(
    @JsonProperty("taxRatePercentage")
    val porcentajeTarifaIva: BigDecimal? = null,
    @JsonProperty("taxRateCode")
    val codigoTarifaIva: String? = null,
    @JsonProperty("taxDescription")
    val descripcionIva: String? = null,
    @JsonProperty("saleAccountId")
    val idCuentaVenta: Int? = null,
    @JsonProperty("purchaseAccountId")
    val idCuentaCompra: Int? = null,
    @JsonProperty("taxRateState")
    val estadoTarifaIva: Boolean = false,
)
