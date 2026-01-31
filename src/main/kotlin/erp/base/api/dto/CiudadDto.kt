package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class CiudadDto(
    @field:NotBlank(message = "El nombre de la provincia no puede estar vacío")
    @JsonProperty("provinceId")
    val idProvincia: String? = null,
    @field:NotBlank(message = "El nombre de la ciudad no puede estar vacío")
    @JsonProperty("cityName")
    val nombreCiudad: String? = null,
    @field:NotBlank(message = "El código de la ciudad no puede estar vacío")
    @JsonProperty("cityCode")
    val codigoCiudad: String? = null,
    @JsonProperty("provinceName")
    val nombreProvincia: String? = null,
)
