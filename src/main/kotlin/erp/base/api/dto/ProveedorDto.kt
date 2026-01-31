package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZonedDateTime

data class ProveedorDto(
    @JsonProperty("taxIdentificationTypeId")
    val idTipoIdFiscal: String? = null,
    @JsonProperty("ivaRetentionId")
    val idRetencionIva: Int? = null,
    @JsonProperty("personId")
    val idPersona: Int? = null,
    @JsonProperty("personName")
    val nombrePersona: String? = null,
    @JsonProperty("bankCatalogId")
    val idBancoCatalogo: Int? = null,
    @JsonProperty("incomeRetentionId")
    val idRetencionFuente: Int? = null,
    @JsonProperty("providerTypeId")
    val idTipoProv: Int? = null,
    @JsonProperty("bankAccountTypeId")
    val idTipoCuentaBancaria: Int? = null,
    @JsonProperty("paymentConditionId")
    val idCondicionPago: Int? = null,
    @JsonProperty("paymentCollectionFormId")
    val idFormaPagoCobro: String? = null,
    @field:Size(min = 5, max = 500, message = "El nombre Comercial Proveedor debe tener entre 5 y 500 caracteres")
    @JsonProperty("providerTradeName")
    val nombreComercialProveedor: String? = null,
    @JsonProperty("providerAddress2")
    @field:Size(min = 5, max = 500, message = "La dirección 2 de proveedor debe tener entre 5 y 500 caracteres")
    val direccion2Proveedor: String? = null,
    @JsonProperty("providerEmail2")
    val correo2Proveedor: String? = null,
    @JsonProperty("providerCellphone2")
    val celular2Proveedor: String? = null,
    @JsonProperty("providerPhone2")
    val telefono2Proveedor: String? = null,
    @JsonProperty("providerWebsite")
    val sitioWebProveedor: String? = null,
    @JsonProperty("providerContact")
    val contactoProveedor: String? = null,
    @JsonProperty("providerContactPosition")
    val cargoContactoProveedor: String? = null,
    @JsonProperty("providerContactPhone")
    val telefonoContactoProveedor: String? = null,
    @JsonProperty("providerContactEmail")
    val correoContactoProveedor: String? = null,
    @JsonProperty("providerContactCellphone")
    val celularContactoProveedor: String? = null,
    @JsonProperty("providerCredit")
    val creditoProveedor: Boolean? = null,
    @JsonProperty("providerCreditAmount")
    val montoCreditoProveedor: BigDecimal? = null,
    @JsonProperty("providerCreditDueDate")
    val fechaVencimientoCreditoProveedor: LocalDate? = null,
    @field:Min(value = 0, message = "El valor de días no puede ser negativo")
    @JsonProperty("providerCreditDays")
    val diasCreditoProveedor: Int? = null,
    @JsonProperty("providerGuaranteeAmount")
    val montoGarantiaProveedor: BigDecimal? = null,
    @JsonProperty("providerBankAccountNumber")
    val numCuentaBancariaProveedor: String? = null,
    @JsonProperty("providerPaymentReference")
    val referenciaPagoProveedor: String? = null,
    @JsonProperty("providerAdvancePaymentBalance")
    val saldoAnticipoProveedor: BigDecimal? = null,
    @JsonProperty("providerCheckName")
    val nombreChequeProveedor: String? = null,
    @JsonProperty("providerSpecialContribution")
    val contribEspecialProveedor: String? = null,
    @JsonProperty("providerTransportation")
    val esTransporteProveedor: Boolean? = null,
    @JsonProperty("providerRegistrationDate")
    val fechaRegistroProveedor: ZonedDateTime? = null,
    @JsonProperty("providerStatus")
    val estadoProveedor: Boolean? = null,
    @JsonProperty("paymentAccountNumber")
    val numeroCuentaPago: String? = null,
    @JsonProperty("fiscalIdentificationPaymentId")
    val idIdentificacionFiscalPago: Int? = null,
    @JsonProperty("providerPassiveAccount")
    val cuentaPasivaProveedor: Int? = null,
    @JsonProperty("providerExpenseAccount")
    val cuentaGastoProveedor: Int? = null,
)
