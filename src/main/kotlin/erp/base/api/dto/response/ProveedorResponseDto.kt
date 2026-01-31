package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import erp.base.api.models.Persona
import erp.base.api.models.Proveedor

data class ProveedorResponseDto(
    @JsonProperty("provider")
    var proveedor: Proveedor? = null,
    @JsonProperty("person")
    var persona: Persona? = null,
    @JsonProperty("parishName")
    var nombreParroquia: String? = null,
    @JsonProperty("cityId")
    var idCiudad: Int? = null,
    @JsonProperty("cityName")
    var nombreCiudad: String? = null,
    @JsonProperty("provinceId")
    var idProvincia: String? = null,
    @JsonProperty("provinceName")
    var nombreProvincia: String? = null,
    @JsonProperty("accountPayableStatus")
    var estadoCuentoPagar: EstadoCuentoPagarDto? = null,
    @JsonProperty("numberInvoices")
    var numeroFacturas: Int? = null,
)
