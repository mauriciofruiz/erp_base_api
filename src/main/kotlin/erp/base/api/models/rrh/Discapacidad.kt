package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("DISCAPACIDAD")
data class Discapacidad(
    @Id
    @Column("ID_DISCAPACIDAD")
    @JsonProperty("disabilityId")
    val idDiscapacidad: Int,
    @Column("NOMBRE_DISCAPACIDAD")
    @JsonProperty("disabilityName")
    val nombreDiscapacidad: String? = null,
)
