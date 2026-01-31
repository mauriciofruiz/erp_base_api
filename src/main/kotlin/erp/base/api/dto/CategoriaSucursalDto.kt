package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class CategoriaSucursalDto(
    @field:NotBlank(message = "El Id de la categoría de la sucursal no puede estar vacío")
    @JsonProperty("branchCategoryId")
    var idCategoriaSucursal: String? = null,
    @field:NotBlank(message = "El nombre de la categoría de la sucursal no puede estar vacío")
    @JsonProperty("branchCategoryName")
    val nombreCategoriaSucursal: String? = null,
    @JsonProperty("branchCategoryDescription")
    val descripcionCategoriaSucursal: String? = null,
)
