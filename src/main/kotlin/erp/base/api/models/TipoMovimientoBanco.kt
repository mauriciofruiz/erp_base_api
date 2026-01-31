package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOMOVIMIENTOBANCO")
data class TipoMovimientoBanco(
    @Id
    @Column("ID_TIPOMOVIMIENTOBANCO")
    @JsonProperty("bankMovementTypeId")
    val idTipoMovimientoBanco: Int,
    @Column("NOMBRE_TIPOMOVIMIENTOBANCO")
    @JsonProperty("bankMovementTypeName")
    val nombreTipoMovimientoBanco: String? = null,
    @Column("ESTADO_TIPOMOVIMIENTOBANCO")
    @JsonProperty("bankMovementTypeState")
    val estadoTipoMovimientoBanco: Boolean? = null,
)
