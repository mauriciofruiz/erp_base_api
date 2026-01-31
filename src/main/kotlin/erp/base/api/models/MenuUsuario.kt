package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("MENU_USUARIO")
data class MenuUsuario(
    @Id
    @Column("ID_MENU_USUARIO")
    @JsonProperty("menuUserId")
    val idMenuUsuario: Int? = null,

    @Column("ESTADO_MENU_USUARIO")
    @JsonProperty("menuUserStatus")
    val estadoMenuUsuario: Boolean? = null,

    @Column("ID_MENU")
    @JsonProperty("menuId")
    val idMenu: Int? = null,

    @Column("ID_USUARIO")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
)
