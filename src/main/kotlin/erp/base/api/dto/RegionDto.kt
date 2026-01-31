package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class RegionDto(
    @field:NotBlank(message = "El nombre de la region no puede estar vacio")
    @JsonProperty("regionName")
    val nombreRegion: String? = null,
)
