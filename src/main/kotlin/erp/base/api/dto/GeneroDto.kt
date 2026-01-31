package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class GeneroDto(
    @field:NotNull(message = "El nombre del género no puede estar vacío.")
    @JsonProperty("genderName")
    val nombreGenero: String? = null,
    @field:NotNull(message = "El código del género no puede estar vacío.")
    @JsonProperty("genderCode")
    val codigoGenero: String? = null,
)
