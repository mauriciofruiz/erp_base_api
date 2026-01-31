package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
data class UsuarioDto(
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
    @JsonProperty("personId")
    val idPersona: Int? = null,
    @JsonProperty("nick")
    val nickUsr: String? = null,
)
