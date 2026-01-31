package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class CargoDto(
    @field:NotNull(message = "El Id del cargo no puede ser nulo")
    @JsonProperty("departmentId")
    val idDepartamento: Int? = null,

    @JsonProperty("positionName")
    val nombreCargo: String? = null,

    @JsonProperty("positionState")
    val estadoCargo: Boolean? = null,
)
