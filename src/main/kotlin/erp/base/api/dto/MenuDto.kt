package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class MenuDto(
    @JsonProperty("menuId")
    var idMenu: Int? = null,
    @JsonProperty("menuIcon")
    var iconMenu: String? = null,
    @JsonProperty("menuLevel")
    var nivelMenu: Int? = null,
    @field:NotNull(message = "El nombre del menu no puede ser nulo")
    @JsonProperty("menuName")
    var nombreMenu: String? = null,
    @field:NotNull(message = "La url del menu no puede ser nula")
    @JsonProperty("menuUrl")
    var urlMenu: String? = null,
    @JsonProperty("menuFatherId")
    var padreMenu: Int? = null,
    @JsonProperty("menuComponente")
    var menuComponent: String? = null,
)
