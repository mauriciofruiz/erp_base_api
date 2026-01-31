package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class MenuUsuarioDto(
    @field:NotNull(message = "El estado del menu usuario no puede ser nulo")
    @JsonProperty("menuUserStatus")
    val estadoMenuUsuario: Boolean? = null,
    @field:NotNull(message = "El id del menu no puede ser nulo")
    @JsonProperty("menuId")
    val idMenu: Int? = null,
    @field:NotNull(message = "El id del usuario no puede ser nulo")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
)
