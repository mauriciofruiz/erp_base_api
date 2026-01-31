package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class PaisDto(
    @field:NotNull(message = "El código del país no puede estar vacío")
    @JsonProperty("countryCode")
    val codigoPais: String? = null,

    @field:NotNull(message = "El nombre del país no puede estar vacío")
    @JsonProperty("countryName")
    val nombrePais: String? = null,
)
