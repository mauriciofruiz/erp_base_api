package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("TBA_MOVIMIENTOBANCO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaMovimientoBanco {
    @Id
    @Column("ID_MOVIMIENTOBANCO")
    @JsonProperty("bankMovementId")
    private Integer idMovimientoBanco;

    @Column("ID_TIPOMOVIMIENTOBANCO")
    @JsonProperty("bankMovementTypeId")
    private Integer idTipoMovimientoBanco;

    @Column("ID_BANCO")
    @JsonProperty("bankId")
    private Integer idBanco;

    @Column("FECHA_MOVIMIENTOBANCO")
    @JsonProperty("bankMovementDate")
    private LocalDateTime fechaMovimientoBanco;

    @Column("NUMERODOC_MOVIMIENTOBANCO")
    @JsonProperty("bankMovementDocumentNumber")
    private String numeroDocumentoMovimientoBanco;

    @Column("VALOR_MOVIMIENTOBANCO")
    @JsonProperty("movementValue")
    private BigDecimal valorMovimientoBanco;

    @Column("REFERENCIA_MOVIMIENTOBANCO")
    @JsonProperty("movementReference")
    private String referenciaMovimientoBanco;

    @Column("SALDOACTUAL_MOVIMIENTOBANCO")
    @JsonProperty("currentBalance")
    private BigDecimal saldoActualMovimientoBanco;
}
