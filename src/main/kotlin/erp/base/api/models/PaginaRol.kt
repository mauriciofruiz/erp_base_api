package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PAGINAROL")
data class PaginaRol(
    @Id
    @Column("ID_PAGINAROL")
    @JsonProperty("pageRoleId")
    val idPaginaRol: Int,
    @Column("ID_ROL")
    @JsonProperty("roleId")
    val idRol: Int? = null,
    @Column("ID_PAGINA")
    @JsonProperty("pageId")
    val idPagina: Int? = null,
    @Column("ESTADO_PAGINAROL")
    @JsonProperty("pageRoleState")
    val estadoPaginaRol: Boolean? = null,
)
