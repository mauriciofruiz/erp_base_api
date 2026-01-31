package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("SUBGRUPO")
data class SubGrupo(
    @Id
    @Column("ID_SUBGRUPO")
    @JsonProperty("subGroupId")
    val idSubGrupo: Int? = null,

    @Column("ID_GRUPO")
    @JsonProperty("groupId")
    val idGrupo: Int? = null,

    @Column("NOMBRE_SUBGRUPO")
    @JsonProperty("subGroupName")
    val nombreSubGrupo: String? = null,

    @Column("NEMONICO_SUBGRUPO")
    @JsonProperty("subGroupMnemonic")
    val nemonicoSubGrupo: String? = null,
)
