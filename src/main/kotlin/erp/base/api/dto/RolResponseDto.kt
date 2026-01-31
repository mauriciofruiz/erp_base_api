package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RolResponseDto(
    @JsonProperty("roleId")
    val idRol: Int? = null,
    @JsonProperty("roleName")
    val nombreRol: String? = null,
    @JsonProperty("roleDescription")
    val descripcionRol: String? = null,
)
