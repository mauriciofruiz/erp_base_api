package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ESTADOPAGO")
data class EstadoPago(
    @Id
    @Column("ID_ESTADOPAGO")
    @JsonProperty("paymentStateId")
    val idEstadoPago: Int,
    @Column("NOMBRE_ESTADOPAGO")
    @JsonProperty("paymentStateName")
    val nombreEstadoPago: String? = null,
)
