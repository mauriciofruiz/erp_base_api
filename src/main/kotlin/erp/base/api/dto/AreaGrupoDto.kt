package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class AreaGrupoDto(
    @field:NotNull(message = "El nombre no puede estar vacío")
    @JsonProperty("areaGroupName")
    val nombreAreaGrupo: String? = null,
)
