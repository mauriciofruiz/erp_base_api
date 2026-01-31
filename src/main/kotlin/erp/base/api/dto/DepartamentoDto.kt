package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class DepartamentoDto(
    @JsonProperty("departmentId")
    val idDepartamento: Int? = null,
    @field:NotBlank(message = "El nombre del departamento no puede estar vacío")
    @JsonProperty("departmentName")
    val nombreDepartamento: String? = null,
    @field:NotNull(message = "El estado del departamento no puede estar vacío")
    @JsonProperty("departmentStatus")
    val estadoDepartamento: Boolean? = null,
    @field:NotNull(message = "El id del grupo de área no puede estar vacío")
    @JsonProperty("areaGroupId")
    val idAreaGrupo: Int? = null,
    @JsonProperty("positionList")
    val tbaCargoDtoList: List<CargoDto>? = null,
)
