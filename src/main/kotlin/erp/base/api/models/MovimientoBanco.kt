package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("MOVIMIENTOBANCO")
data class MovimientoBanco(
    @Id
    @Column("ID_MOVIMIENTOBANCO")
    @JsonProperty("bankMovementId")
    val idMovimientoBanco: Int,
    @Column("ID_TIPOMOVIMIENTOBANCO")
    @JsonProperty("bankMovementTypeId")
    val idTipoMovimientoBanco: Int? = null,
    @Column("ID_BANCO")
    @JsonProperty("bankId")
    val idBanco: Int? = null,
    @Column("FECHA_MOVIMIENTOBANCO")
    @JsonProperty("bankMovementDate")
    val fechaMovimientoBanco: LocalDateTime? = null,
    @Column("NUMERODOC_MOVIMIENTOBANCO")
    @JsonProperty("bankMovementDocumentNumber")
    val numeroDocumentoMovimientoBanco: String? = null,
    @Column("VALOR_MOVIMIENTOBANCO")
    @JsonProperty("movementValue")
    val valorMovimientoBanco: BigDecimal? = null,
    @Column("REFERENCIA_MOVIMIENTOBANCO")
    @JsonProperty("movementReference")
    val referenciaMovimientoBanco: String? = null,
    @Column("SALDOACTUAL_MOVIMIENTOBANCO")
    @JsonProperty("currentBalance")
    val saldoActualMovimientoBanco: BigDecimal? = null,
)
