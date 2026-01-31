package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProvinciaDto(
    @field:NotBlank(message = "El id de la provincia no puede estar vacío")
    @JsonProperty("provinceId")
    var idProvincia: String? = null,
    @field:NotNull(message = "El id del país no puede estar vacío")
    @JsonProperty("countryId")
    val idPais: Int? = null,
    @field:NotBlank(message = "El nombre de la provincia no puede estar vacío")
    @JsonProperty("provinceName")
    val nombreProvincia: String? = null,
    @field:NotNull(message = "El código de la provincia no puede estar vacío")
    @JsonProperty("regionCode")
    val codRegion: Int? = null,
)
