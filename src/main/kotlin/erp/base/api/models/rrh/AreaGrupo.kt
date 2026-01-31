package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("AREAGRUPO")
data class AreaGrupo(
    @Id
    @Column("ID_AREAGRUPO")
    @JsonProperty("areaGroupId")
    val idAreaGrupo: Int? = null,

    @Column("NOMBRE_AREAGRUPO")
    @JsonProperty("areaGroupName")
    val nombreAreaGrupo: String? = null,
)
