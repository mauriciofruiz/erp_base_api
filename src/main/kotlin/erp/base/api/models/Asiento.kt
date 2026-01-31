package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Table("ASIENTO")
data class Asiento(
    @Id
    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    val idAsiento: Int,
    @Column("ID_ESTADOASIENTO")
    @JsonProperty("accountingEntryStateId")
    val idEstadoAsiento: Int? = null,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @Column("ID_TIPOMOVIMIENTO")
    @JsonProperty("movementTypeId")
    val idTipoMovimiento: Int? = null,
    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
    @Column("REFERENCIA_ASIENTO")
    @JsonProperty("reference")
    val referenciaAsiento: String? = null,
    @Column("OBS_ASIENTO")
    @JsonProperty("observations")
    val observacionesAsiento: String? = null,
    @Column("DEBETOTAL_ASIENTO")
    @JsonProperty("totalDebit")
    val debeTotalAsiento: BigDecimal? = null,
    @Column("HABERTOTAL_ASIENTO")
    @JsonProperty("haveTotalCredit")
    val haberTotalCredito: BigDecimal? = null,
    @Column("FECHA_ASIENTO")
    @JsonProperty("accountingSeat")
    val fechaAsiento: LocalDate? = null,
    @Column("FECHAMODIFICACION_ASIENTO")
    @JsonProperty("modificationDate")
    val fechaModificacionAsiento: LocalDateTime? = null,
    @Column("FECHACONTABILIDAD_ASIENTO")
    @JsonProperty("accountingDate")
    val fechaContabilidadAsiento: LocalDateTime? = null,
    @Column("AUTOMATICO_ASIENTO")
    @JsonProperty("automatic")
    val automaticoAsiento: Boolean = false,
    @Column("MOTIVOMODIFICACION_ASIENTO")
    @JsonProperty("modificationReason")
    val motivoModificacionAsiento: String? = null,
    @Column("MOTIVOANULACION_ASIENTO")
    @JsonProperty("cancellationReason")
    val motivoAnulacionAsiento: String? = null,
    @Column("DOCEXTERNO_ASIENTO")
    @JsonProperty("externalDocument")
    val documentoExternoAsiento: String? = null,
    @Column("CODUSRCREA_ASIENTO")
    @JsonProperty("creationUserCode")
    val codigoUsuarioCreacionAsiento: String? = null,
    @Column("ANULADO_ASIENTO")
    @JsonProperty("cancelled")
    val anuladoAsiento: Boolean = false,
    @Column("NUMDOC_ASIENTO")
    @JsonProperty("documentNumber")
    val numeroDocumentoAsiento: String? = null,
    @Column("REVISADO_ASIENTO")
    @JsonProperty("reviewed")
    val revisadoAsiento: Boolean? = null,
    @Column("CODUSRANULA_ASIENTO")
    @JsonProperty("cancellationUserCode")
    val codigoUsuarioAnulacionAsiento: Int? = null,
)
