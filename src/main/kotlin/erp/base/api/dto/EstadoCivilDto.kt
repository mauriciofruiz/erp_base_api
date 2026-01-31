package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class EstadoCivilDto(
    @field:NotNull(message = "El nombre del estado civil no puede estar vacío.")
    @JsonProperty("maritalStatusName")
    val nombreEstadoCivil: String? = null,
    @field:NotNull(message = "El código del estado civil no puede estar vacío.")
    @JsonProperty("maritalStatusCode")
    val codigoEstadoCivil: String? = null,
)
