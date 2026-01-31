package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ESTADODEPOSITO")
data class EstadoDeposito(
    @Id
    @Column("ID_ESTADODEPOSITO")
    @JsonProperty("depositStateId")
    val idEstadoDeposito: Int,
    @Column("NOMBRE_ESTADODEPOSITO")
    @JsonProperty("depositStateName")
    val nombreEstadoDeposito: String? = null,
)
