package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ESTADOENTREGA")
data class EstadoEntrega(
    @Id
    @Column("ID_ESTADOENTREGA")
    @JsonProperty("deliveryStatusId")
    val idEstadoEntrega: Int,
    @Column("NOMBRE_ESTADOENTREGA")
    @JsonProperty("deliveryStatusName")
    val nombreEstadoEntrega: String? = null,
)
