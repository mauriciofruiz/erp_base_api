package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import erp.base.api.models.TbaRolUsuario

data class UsuarioRolesDto(
    @JsonProperty("nick")
    val nickUsr: String? = null,
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
    @JsonProperty("personId")
    val idPersona: Int? = null,
    @JsonProperty("position")
    val cargoUsr: Int? = null,
    @JsonProperty("startTime")
    val horaInicioUsr: String? = null,
    @JsonProperty("endTime")
    val horaFinUsr: String? = null,
    @JsonProperty("stateUser")
    val estado: Boolean = false,
    @JsonProperty("photo")
    val imgUsr: String? = null,
    @JsonProperty("cashId")
    val idCaja: Int? = null,
    @JsonProperty("rolesList")
    val detailRol: List<TbaRolUsuario>? = null,
)
