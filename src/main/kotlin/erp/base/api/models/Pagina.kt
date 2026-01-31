package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PAGINA")
data class Pagina(
    @Id
    @Column("ID_PAGINA")
    @JsonProperty("pageId")
    val idPagina: Int,
    @Column("URL_PAGINA")
    @JsonProperty("pageUrl")
    val urlPagina: String? = null,
    @Column("ALIAS_PAGINA")
    @JsonProperty("pageAlias")
    val aliasPagina: String? = null,
    @Column("SECCION_PAGINA")
    @JsonProperty("pageSection")
    val seccionPagina: String? = null,
)
