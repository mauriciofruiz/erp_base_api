package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("MENU")
data class Menu(
    @Id
    @Column("ID_MENU")
    @JsonProperty("menuId")
    val idMenu: Int? = null,

    @Column("ICON_MENU")
    @JsonProperty("menuIcon")
    val iconMenu: String? = null,

    @Column("NIVEL_MENU")
    @JsonProperty("menuLevel")
    val nivelMenu: Int? = null,

    @Column("NOMBRE_MENU")
    @JsonProperty("menuName")
    val nombreMenu: String? = null,

    @Column("URL_MENU")
    @JsonProperty("menuUrl")
    val urlMenu: String? = null,

    @Column("PADRE_MENU")
    @JsonProperty("menuFatherId")
    val padreMenu: Int? = null,

    @Column("COMPONENT_MENU")
    @JsonProperty("menuComponent")
    val componenteMenu: String? = null,

    @Column("ORDEN_MENU")
    @JsonProperty("menuOrder")
    val ordenMenu: Int? = null,

    @Column("ESTADO_MENU")
    @JsonProperty("menuStatus")
    val estadoMenu: Boolean? = null,
)
