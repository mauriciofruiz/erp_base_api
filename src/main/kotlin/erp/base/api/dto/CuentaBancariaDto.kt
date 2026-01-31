package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CuentaBancariaDto(
    @JsonProperty("countBankId")
    val idCuentabancaria: Int? = null,
    @JsonProperty("catalogBank")
    val idBancocatalogo: Int? = null,
    @JsonProperty("bankName")
    val nombreBancocatalogo: String? = null,
    @JsonProperty("bankAccountTypeId")
    val idTipocuentabancaria: Int? = null,
    @JsonProperty("accountTypeName")
    val nombreTipocuentabancaria: String? = null,
    @JsonProperty("bankAccountNumber")
    val numeroCuentabancaria: String? = null,
    @JsonProperty("accountStatusBank")
    val estadoCuentabancaria: Boolean? = null,
)
