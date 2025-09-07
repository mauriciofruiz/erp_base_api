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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("CON_ASIENTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConAsiento {
    @Id
    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    private Integer idAsiento;

    @Column("ID_ESTADOASIENTO")
    @JsonProperty("accountingEntryStateId")
    private Integer idEstadoAsiento;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("ID_TIPOMOVIMIENTO")
    @JsonProperty("movementTypeId")
    private Integer idTipoMovimiento;

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("REFERENCIA_ASIENTO")
    @JsonProperty("reference")
    private String referenciaAsiento;

    @Column("OBS_ASIENTO")
    @JsonProperty("observations")
    private String observacionesAsiento;

    @Column("DEBETOTAL_ASIENTO")
    @JsonProperty("totalDebit")
    private BigDecimal debeTotalAsiento;

    @Column("HABERTOTAL_ASIENTO")
    @JsonProperty("haveTotalCredit")
    private BigDecimal haberTotalCredito;

    @Column("FECHA_ASIENTO")
    @JsonProperty("accountingSeat")
    private LocalDate fechaAsiento;

    @Column("FECHAMODIFICACION_ASIENTO")
    @JsonProperty("modificationDate")
    private LocalDateTime fechaModificacionAsiento;

    @Column("FECHACONTABILIDAD_ASIENTO")
    @JsonProperty("accountingDate")
    private LocalDateTime fechaContabilidadAsiento;

    @Column("AUTOMATICO_ASIENTO")
    @JsonProperty("automatic")
    private boolean automaticoAsiento;

    @Column("MOTIVOMODIFICACION_ASIENTO")
    @JsonProperty("modificationReason")
    private String motivoModificacionAsiento;

    @Column("MOTIVOANULACION_ASIENTO")
    @JsonProperty("cancellationReason")
    private String motivoAnulacionAsiento;

    @Column("DOCEXTERNO_ASIENTO")
    @JsonProperty("externalDocument")
    private String documentoExternoAsiento;

    @Column("CODUSRCREA_ASIENTO")
    @JsonProperty("creationUserCode")
    private String codigoUsuarioCreacionAsiento;

    @Column("ANULADO_ASIENTO")
    @JsonProperty("cancelled")
    private boolean anuladoAsiento;

    @Column("NUMDOC_ASIENTO")
    @JsonProperty("documentNumber")
    private String numeroDocumentoAsiento;

    @Column("REVISADO_ASIENTO")
    @JsonProperty("reviewed")
    private Boolean revisadoAsiento;

    @Column("CODUSRANULA_ASIENTO")
    @JsonProperty("cancellationUserCode")
    private Integer codigoUsuarioAnulacionAsiento;
}
