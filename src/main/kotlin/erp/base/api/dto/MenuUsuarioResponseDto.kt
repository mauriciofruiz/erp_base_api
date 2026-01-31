package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MenuUsuarioResponseDto(
    @JsonProperty("menuId")
    val idMenu: Int? = null,
    @JsonProperty("menuName")
    val nombreMenu: String? = null,
    @JsonProperty("menuFatherId")
    val padreMenu: Int? = null,
    @JsonProperty("menuLevel")
    val nivelMenu: Int? = null,
    @JsonProperty("menuIcon")
    val iconMenu: String? = null,
    @JsonProperty("menuUrl")
    val urlMenu: String? = null,
    @JsonProperty("menuComponent")
    val componentMenu: String? = null,
    @JsonProperty("menuState")
    val estadoMenuUsuario: Boolean? = null,
)
