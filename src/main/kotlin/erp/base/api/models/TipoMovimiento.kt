package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOMOVIMIENTO")
data class TipoMovimiento(
    @Id
    @Column("ID_TIPOMOVIMIENTO")
    @JsonProperty("movementTypeId")
    val idTipoMovimiento: Int,

    @Column("NOMBRE_TIPOMOVIMIENTO")
    @JsonProperty("movementTypeName")
    val nombreTipoMovimiento: String? = null,
)
