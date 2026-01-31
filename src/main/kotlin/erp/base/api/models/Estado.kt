package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ESTADO")
data class Estado(
    @Id
    @Column("ID_ESTADO")
    @JsonProperty("stateId")
    val idEstado: Int? = null,
    @Column("NOMBRE_ESTADO")
    @JsonProperty("stateName")
    val nombreEstado: String? = null,
)

