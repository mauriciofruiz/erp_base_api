package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Table("PROVEEDOR")
data class Proveedor(
    @Column("id_usuario_creacion")
    @JsonProperty("creationUserId")
    var idUsuarioCreacion: Int? = null,
    @Column("id_usuario_modificacion")
    @JsonProperty("modificationUserId")
    var idUsuarioModificacion: Int? = null,
    @Column("fecha_creacion")
    @JsonProperty("registrationDate")
    var fechaCreacion: LocalDateTime? = null,
    @Column("fecha_modificacion")
    @JsonProperty("modificationDate")
    var fechaModificacion: LocalDateTime? = null,
    @Id
    @Column("id_proveedor")
    @JsonProperty("providerId")
    var idProveedor: Int? = null,
    @Column("id_tipoidfiscal")
    @JsonProperty("fiscalTypeId")
    var idTipoIdFiscal: String? = null,
    @Column("id_retencioniva")
    @JsonProperty("vatRetentionId")
    var idRetencionIva: Int? = null,
    @Column("id_persona")
    @JsonProperty("personId")
    var idPersona: Int? = null,
    @Column("id_bancocatalogo")
    @JsonProperty("bankCatalogId")
    var idBancoCatalogo: Int? = null,
    @Column("id_retencionfuente")
    @JsonProperty("sourceRetentionId")
    var idRetencionFuente: Int? = null,
    @Column("id_tipoprov")
    @JsonProperty("providerTypeId")
    var idTipoProveedor: Int? = null,
    @Column("id_tipocuentabancaria")
    @JsonProperty("bankAccountTypeId")
    var idTipoCuentaBancaria: Int? = null,
    @Column("id_condicionpago")
    @JsonProperty("paymentConditionId")
    var idCondicionPago: Int? = null,
    @Column("id_formapagocobro")
    @JsonProperty("paymentMethodId")
    var idFormaPagoCobro: String? = null,
    @Column("nombrecomercial_proveedor")
    @JsonProperty("providerCommercialName")
    var nombreComercialProveedor: String? = null,
    @Column("direccion2_proveedor")
    @JsonProperty("providerAddress2")
    var direccion2Proveedor: String? = null,
    @Column("correo2_proveedor")
    @JsonProperty("providerEmail2")
    var correo2Proveedor: String? = null,
    @Column("celular2_proveedor")
    @JsonProperty("providerCellphone2")
    var celular2Proveedor: String? = null,
    @Column("telefono2_proveedor")
    @JsonProperty("providerPhone2")
    var telefono2Proveedor: String? = null,
    @Column("sitioweb_proveedor")
    @JsonProperty("providerWebsite")
    var sitioWebProveedor: String? = null,
    @Column("contacto_proveedor")
    @JsonProperty("providerContact")
    var contactoProveedor: String? = null,
    @Column("cargocontacto_proveedor")
    @JsonProperty("providerContactPosition")
    var cargoContactoProveedor: String? = null,
    @Column("telefonocontacto_proveedor")
    @JsonProperty("providerContactPhone")
    var telefonoContactoProveedor: String? = null,
    @Column("correocontacto_proveedor")
    @JsonProperty("providerContactEmail")
    var correoContactoProveedor: String? = null,
    @Column("celularcontacto_proveedor")
    @JsonProperty("providerContactCellphone")
    var celularContactoProveedor: String? = null,
    @Column("credito_proveedor")
    @JsonProperty("isCredit")
    var creditoProveedor: Boolean? = null,
    @Column("montocredito_proveedor")
    @JsonProperty("creditAmount")
    var montoCreditoProveedor: BigDecimal? = null,
    @Column("fechavencimientocredito_proveedor")
    @JsonProperty("creditExpirationDate")
    var fechaVencimientoCreditoProveedor: LocalDate? = null,
    @Column("diascredito_proveedor")
    @JsonProperty("creditDays")
    var diasCreditoProveedor: Int? = null,
    @Column("montogarantia_proveedor")
    @JsonProperty("warrantyAmount")
    var montoGarantiaProveedor: BigDecimal? = null,
    @Column("numcuentabancaria_proveedor")
    @JsonProperty("providerBankAccountNumber")
    var numeroCuentaBancariaProveedor: String? = null,
    @Column("referenciapago_proveedor")
    @JsonProperty("paymentReference")
    var referenciaPagoProveedor: String? = null,
    @Column("saldoanticipo_proveedor")
    @JsonProperty("advancePaymentBalance")
    var saldoAnticipoProveedor: BigDecimal? = null,
    @Column("nombrecheque_proveedor")
    @JsonProperty("providerCheckName")
    var nombreChequeProveedor: String? = null,
    @Column("contribespecial_proveedor")
    @JsonProperty("specialContributor")
    var contribucionEspecialProveedor: String? = null,
    @Column("estransporte_proveedor")
    @JsonProperty("isTransporter")
    var esTransporteProveedor: Boolean? = null,
    @Column("estado_proveedor")
    @JsonProperty("providerStatus")
    var estadoProveedor: Boolean? = null,
    @Column("numero_cuenta_pago")
    @JsonProperty("paymentAccountNumber")
    var numeroCuentaPago: String? = null,
    @Column("id_identificacionfiscal_pago")
    @JsonProperty("fiscalIdentificationPaymentId")
    var idIdentificacionFiscalPago: Int? = null,
    @Column("cuentapasiva_proveedor")
    @JsonProperty("passiveAccount")
    var cuentaPasivaProveedor: Int? = null,
    @Column("cuentagasto_proveedor")
    @JsonProperty("expenseAccount")
    var cuentaGastoProveedor: Int? = null,
)
