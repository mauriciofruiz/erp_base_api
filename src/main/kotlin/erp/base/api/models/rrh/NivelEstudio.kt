package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("NIVELESTUDIO")
data class NivelEstudio(
    @Id
    @Column("ID_NIVELESTUDIO")
    @JsonProperty("educationLevelId")
    val idNivelEstudio: Int,
    @Column("NOMBRE_NIVELESTUDIO")
    @JsonProperty("educationLevelName")
    val nombreNivelEstudio: String? = null,
)
