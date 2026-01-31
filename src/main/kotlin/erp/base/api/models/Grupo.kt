package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("GRUPO")
data class Grupo(
    @Id
    @Column("ID_GRUPO")
    @JsonProperty("groupId")
    val idGrupo: Int? = null,

    @Column("NOMBRE_GRUPO")
    @JsonProperty("groupName")
    val nombreGrupo: String? = null,

    @Column("NEMONICO_GRUPO")
    @JsonProperty("groupNemónico")
    val nemonicoGrupo: String? = null,

    @Column("CTAING_GRUPO")
    @JsonProperty("groupCtaIng")
    val ctaIngGrupo: Int? = null,

    @Column("CTAINV_GRUPO")
    @JsonProperty("groupCtaInv")
    val ctaInvGrupo: Int? = null,

    @Column("CTACOS_GRUPO")
    @JsonProperty("groupCtaCos")
    val ctaCosGrupo: Int? = null,
)
