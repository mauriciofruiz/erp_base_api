package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class SucursalDto(
    @field:NotNull(message = "El id de la ciudad no puede ser nulo")
    @JsonProperty("cityId")
    val idCiudad: Int? = null,
    @field:NotBlank(message = "El id de la categoria de sucursal no puede ser nulo")
    @JsonProperty("branchCategoryId")
    val idCategoriaSucursal: String? = null,
    @field:NotNull(message = "El id de la region no puede ser nulo")
    @JsonProperty("regionId")
    val idRegion: Int? = null,
    @field:NotBlank(message = "El nombre de la sucursal no puede estar vacío")
    @JsonProperty("name")
    val nombreSucursal: String? = null,
    @field:NotBlank(message = "El nemonico de la sucursal no puede estar vacío")
    @JsonProperty("mnemonic")
    val nemonicoSucursal: String? = null,
    @field:NotBlank(message = "La dirección de la sucursal no puede estar vacía")
    @JsonProperty("address")
    val direccionSucursal: String? = null,
    @JsonProperty("phone")
    val telefonoSucursal: String? = null,
    @JsonProperty("phone2")
    val telefono2Sucursal: String? = null,
    @JsonProperty("cellphone")
    val celularSucursal: String? = null,
    @JsonProperty("email")
    val correoSucursal: String? = null,
    @JsonProperty("email2")
    val correo2Sucursal: String? = null,
    @JsonProperty("responsible")
    val responsableSucursal: String? = null,
    @JsonProperty("isOutlet")
    val esOutletSucursal: Boolean? = null,
    @JsonProperty("state")
    val estadoSucursal: Boolean? = null,
    @JsonProperty("thermalPrinting")
    val impresionTermicaSucursal: Boolean? = null,
    @JsonProperty("branchCode")
    val codigoSucursal: String? = null,
    @JsonProperty("wareHouseDetail")
    val bodegaList: List<BodegaDto>? = null,
)
