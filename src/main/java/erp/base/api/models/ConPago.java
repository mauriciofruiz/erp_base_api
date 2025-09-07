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

@Table("CON_PAGO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConPago {
    @Id
    @Column("ID_PAGO")
    @JsonProperty("paymentId")
    private Integer idPago;

    @Column("ID_ASIENTO")
    @JsonProperty("accountingEntryId")
    private Integer idAsiento;

    @Column("ID_FORMAPAGOCOBRO")
    @JsonProperty("paymentMethodId")
    private String idFormaPagoCobro;

    @Column("ID_BANCO")
    @JsonProperty("bankId")
    private Integer idBanco;

    @Column("ID_ESTADOPAGO")
    @JsonProperty("paymentStateId")
    private Integer idEstadoPago;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("BENEFICIARIO_PAGO")
    @JsonProperty("paymentBeneficiary")
    private String beneficiarioPago;

    @Column("IDENBENEFICIARIO_PAGO")
    @JsonProperty("paymentBeneficiaryIdentification")
    private String identificacionBeneficiarioPago;

    @Column("NUMERODOCUMENTO_PAGO")
    @JsonProperty("paymentDocumentNumber")
    private String numeroDocumentoPago;

    @Column("MONTO_PAGO")
    @JsonProperty("paymentAmount")
    private BigDecimal montoPago;

    @Column("VALCRUZADO_PAGO")
    @JsonProperty("crossedValue")
    private BigDecimal valorCruzadoPago;

    @Column("NUMCUENTA_PAGO")
    @JsonProperty("paymentAccountNumber")
    private String numeroCuentaPago;

    @Column("TIPOCUENTA_PAGO")
    @JsonProperty("paymentAccountType")
    private String tipoCuentaPago;

    @Column("CONCEPTO_PAGO")
    @JsonProperty("paymentConcept")
    private String conceptoPago;

    @Column("BANCO_PAGO")
    @JsonProperty("paymentBank")
    private String bancoPago;

    @Column("FECHAREGISTRO_PAGO")
    @JsonProperty("paymentRegistrationDate")
    private LocalDateTime fechaRegistroPago;

    @Column("FECHACONTABILIDAD_PAGO")
    @JsonProperty("paymentAccountingDate")
    private LocalDate fechaContabilidadPago;

    @Column("FECHACONFIRMACION_PAGO")
    @JsonProperty("paymentConfirmationDate")
    private LocalDate fechaConfirmacionPago;

    @Column("CHEQUE_PAGO")
    @JsonProperty("paymentCheck")
    private boolean chequePago;

    @Column("CODCUENTA_PAGO")
    @JsonProperty("paymentAccountCode")
    private String codigoCuentaPago;

    @Column("CODCALENDARIO_PAGO")
    @JsonProperty("paymentCalendarCode")
    private String codigoCalendarioPago;

    @Column("ESCAJACHICA_PAGO")
    @JsonProperty("paymentPettyCash")
    private boolean cajaChicaPago;

    @Column("ESMANUAL_PAGO")
    @JsonProperty("paymentManual")
    private boolean manualPago;

    @Column("TIPO_PAGO")
    @JsonProperty("paymentType")
    private boolean tipoPago;

    @Column("RECUPERARCHEQUE_PAGO")
    @JsonProperty("recoverCheck")
    private boolean recuperarChequePago;

    @Column("ANULADO_PAGO")
    @JsonProperty("paymentCancelled")
    private boolean anuladoPago;

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("FECHAVALIDACION_PAGO")
    @JsonProperty("paymentValidationDate")
    private LocalDate fechaValidacionPago;

    @Column("FECHACONCILIA_PAGO")
    @JsonProperty("paymentReconciliationDate")
    private LocalDate fechaConciliacionPago;
}
