package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ESTADOASIENTO")
data class EstadoAsiento(
    @Id
    @Column("ID_ESTADOASIENTO")
    @JsonProperty("accountingEntryStateId")
    val idEstadoAsiento: Int,
    @Column("NOMBRE_ESTADOASIENTO")
    @JsonProperty("accountingEntryStateName")
    val nombreEstadoAsiento: String? = null,
)
