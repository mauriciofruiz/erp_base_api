package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ESTADOSRI")
data class EstadoSRI(
    @Id
    @Column("ID_ESTADOSRI")
    @JsonProperty("sriStateId")
    val idEstadoSRI: Int,
    @Column("NOMBRE_ESTADOSRI")
    @JsonProperty("sriStateName")
    val nombreEstadoSRI: String? = null,
)
