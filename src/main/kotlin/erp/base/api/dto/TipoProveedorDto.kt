package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class TipoProveedorDto(
    @field:NotBlank(message = "El nombre del tipo de proveedor no puede estar vacío")
    @JsonProperty("vendorTypeName")
    val nombreTipoProv: String? = null,
)
