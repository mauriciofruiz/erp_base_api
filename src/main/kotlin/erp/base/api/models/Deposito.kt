package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Table("DEPOSITO")
data class Deposito(
    @Id
    @Column("ID_DEPOSITO")
    @JsonProperty("depositId")
    val idDeposito: Int,
    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
    @Column("ID_BANCO")
    @JsonProperty("bankId")
    val idBanco: Int? = null,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsr: Int? = null,
    @Column("ID_ASIENTO")
    @JsonProperty("ledgerId")
    val idAsiento: Int? = null,
    @Column("ID_ESTADODEPOSITO")
    @JsonProperty("depositStatusId")
    val idEstadoDeposito: Int? = null,
    @Column("NUMREF_DEPOSITO")
    @JsonProperty("depositRefNumber")
    val numRefDeposito: String? = null,
    @Column("FECHA_DEPOSITO")
    @JsonProperty("depositDate")
    val fechaDeposito: LocalDate? = null,
    @Column("MONTO_DEPOSITO")
    @JsonProperty("depositAmount")
    val montoDeposito: BigDecimal? = null,
    @Column("OBS_DEPOSITO")
    @JsonProperty("depositNotes")
    val obsDeposito: String? = null,
    @Column("FECHAREGISTRO_DEPOSITO")
    @JsonProperty("depositRegistrationDate")
    val fechaRegistroDeposito: LocalDateTime? = null,
    @Column("FECHAANULA_DEPOSITO")
    @JsonProperty("depositCancellationDate")
    val fechaAnulacionDeposito: LocalDateTime? = null,
    @Column("MOTIVOANULA_DEPOSITO")
    @JsonProperty("depositCancellationReason")
    val motivoAnulacionDeposito: String? = null,
    @Column("FECHACONCILIA_DEPOSITO")
    @JsonProperty("depositReconciliationDate")
    val fechaConciliacionDeposito: LocalDate? = null,
    @Column("REFERENCIA_DEPOSITO")
    @JsonProperty("depositReference")
    val referenciaDeposito: String? = null,
)
