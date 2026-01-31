package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PARENTESCO")
data class Parentesco(
    @Id
    @Column("ID_PARENTESCO")
    @JsonProperty("kinId")
    val idParentesco: Int,
    @Column("NOMBRE_PARENTESCO")
    @JsonProperty("kinName")
    val nombreParentesco: String? = null,
    @Column("CARGAFAMILIAR_PARENTESCO")
    @JsonProperty("familyBurdenKin")
    val cargaFamiliarParentesco: Boolean? = null,
)
