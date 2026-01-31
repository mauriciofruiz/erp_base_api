package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CONDICIONPAGO")
data class CondicionPago(
    @Id
    @Column("ID_CONDICIONPAGO")
    @JsonProperty("paymentConditionId")
    val idCondicionPago: Int? = null,

    @Column("NOMBRE_CONDICIONPAGO")
    @JsonProperty("paymentConditionName")
    val nombreCondicionPago: String? = null,
)
