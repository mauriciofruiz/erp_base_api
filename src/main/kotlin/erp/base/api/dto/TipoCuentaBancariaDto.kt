package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class TipoCuentaBancariaDto(
    @field:NotBlank(message = "El nombre de tipo cuenta no puede estar vacío")
    @JsonProperty("accountTypeName")
    val nombreTipoCuentaBancaria: String? = null,
    @field:NotBlank(message = "La descripción de la cuenta no puede estar vacío")
    @JsonProperty("accountTypeMnemonic")
    val nemonicoTipoCuentaBancaria: String? = null,
)
