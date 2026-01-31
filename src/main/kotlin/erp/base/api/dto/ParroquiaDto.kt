package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class ParroquiaDto(
    @JsonProperty("parishId")
    val idParroquia: Int? = null,
    @field:NotNull(message = "El Id de la ciudad no puede estar vacío")
    @JsonProperty("cityId")
    val idCiudad: Int? = null,
    @field:NotNull(message = "El nombre de la parroquia no puede estar vacío")
    @JsonProperty("parishName")
    val nombreParroquia: String? = null,
    @field:NotNull(message = "El código de la parroquia no puede estar vacío")
    @JsonProperty("parishCode")
    val codParroquia: String? = null,
)
