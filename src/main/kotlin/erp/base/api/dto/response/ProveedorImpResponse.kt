package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ProveedorImpResponse(
    @JsonProperty("providerId")
    var proveedorId: Int? = null,
    @JsonProperty("providerName")
    var nombreProveedor: String? = null,
    @JsonProperty("providerAddress")
    var direccionProveedor: String? = null,
    @JsonProperty("contact")
    var contacto: String? = null,
    @JsonProperty("identification")
    var identificacion: String? = null,
    @JsonProperty("emailList")
    var correoList: List<String>? = null,
    @JsonProperty("phoneList")
    var telefonoList: List<String>? = null,
)
