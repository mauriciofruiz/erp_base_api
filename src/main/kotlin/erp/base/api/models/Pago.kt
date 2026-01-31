package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Table("PAGO")
data class Pago(
    @Id
    @Column("ID_PAGO")
    @JsonProperty("paymentId")
    val idPago: Int,
    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    val idAsiento: Int? = null,
    @Column("ID_FORMAPAGOCOBRO")
    @JsonProperty("paymentMethodId")
    val idFormaPagoCobro: String? = null,
    @Column("ID_BANCO")
    @JsonProperty("bankId")
    val idBanco: Int? = null,
    @Column("ID_ESTADOPAGO")
    @JsonProperty("paymentStateId")
    val idEstadoPago: Int? = null,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @Column("BENEFICIARIO_PAGO")
    @JsonProperty("paymentBeneficiary")
    val beneficiarioPago: String? = null,
    @Column("IDENBENEFICIARIO_PAGO")
    @JsonProperty("paymentBeneficiaryIdentification")
    val identificacionBeneficiarioPago: String? = null,
    @Column("NUMERODOCUMENTO_PAGO")
    @JsonProperty("paymentDocumentNumber")
    val numeroDocumentoPago: String? = null,
    @Column("MONTO_PAGO")
    @JsonProperty("paymentAmount")
    val montoPago: BigDecimal? = null,
    @Column("VALCRUZADO_PAGO")
    @JsonProperty("crossedValue")
    val valorCruzadoPago: BigDecimal? = null,
    @Column("NUMCUENTA_PAGO")
    @JsonProperty("paymentAccountNumber")
    val numeroCuentaPago: String? = null,
    @Column("TIPOCUENTA_PAGO")
    @JsonProperty("paymentAccountType")
    val tipoCuentaPago: String? = null,
    @Column("CONCEPTO_PAGO")
    @JsonProperty("paymentConcept")
    val conceptoPago: String? = null,
    @Column("BANCO_PAGO")
    @JsonProperty("paymentBank")
    val bancoPago: String? = null,
    @Column("FECHAREGISTRO_PAGO")
    @JsonProperty("paymentRegistrationDate")
    val fechaRegistroPago: LocalDateTime? = null,
    @Column("FECHACONTABILIDAD_PAGO")
    @JsonProperty("paymentAccountingDate")
    val fechaContabilidadPago: LocalDate? = null,
    @Column("FECHACONFIRMACION_PAGO")
    @JsonProperty("paymentConfirmationDate")
    val fechaConfirmacionPago: LocalDate? = null,
    @Column("CHEQUE_PAGO")
    @JsonProperty("paymentCheck")
    val chequePago: Boolean = false,
    @Column("CODCUENTA_PAGO")
    @JsonProperty("paymentAccountCode")
    val codigoCuentaPago: String? = null,
    @Column("CODCALENDARIO_PAGO")
    @JsonProperty("paymentCalendarCode")
    val codigoCalendarioPago: String? = null,
    @Column("ESCAJACHICA_PAGO")
    @JsonProperty("paymentPettyCash")
    val cajaChicaPago: Boolean = false,
    @Column("ESMANUAL_PAGO")
    @JsonProperty("paymentManual")
    val manualPago: Boolean = false,
    @Column("TIPO_PAGO")
    @JsonProperty("paymentType")
    val tipoPago: Boolean = false,
    @Column("RECUPERARCHEQUE_PAGO")
    @JsonProperty("recoverCheck")
    val recuperarChequePago: Boolean = false,
    @Column("ANULADO_PAGO")
    @JsonProperty("paymentCancelled")
    val anuladoPago: Boolean = false,
    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
    @Column("FECHAVALIDACION_PAGO")
    @JsonProperty("paymentValidationDate")
    val fechaValidacionPago: LocalDate? = null,
    @Column("FECHACONCILIA_PAGO")
    @JsonProperty("paymentReconciliationDate")
    val fechaConciliacionPago: LocalDate? = null,
)
