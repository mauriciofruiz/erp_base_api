package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("FACTURAPROVEEDOR")
data class FacturaProveedor(
    @Id
    @Column("id_facturaproveedor")
    @JsonProperty("invoiceProviderId")
    val idFacturaProveedor: Int,
    @Column("id_asiento")
    @JsonProperty("seatId")
    val idAsiento: Int? = null,
    @Column("id_formapagocobro")
    @JsonProperty("paymentMethodId")
    val idFormaPagoCobro: String? = null,
    @Column("id_proveedor")
    @JsonProperty("providerId")
    val idProveedor: Int? = null,
    @Column("id_usr")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @Column("id_condicionpago")
    @JsonProperty("paymentConditionId")
    val idCondicionPago: Int? = null,
    @Column("id_tipopago")
    @JsonProperty("paymentTypeId")
    val idTipoPago: Int? = null,
    @Column("id_estadofacprov")
    @JsonProperty("invoiceProviderStateId")
    val idEstadoFacturaProveedor: Int? = null,
    @Column("id_sucursal")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
    @Column("id_tipoliquidacion")
    @JsonProperty("liquidationTypeId")
    val idTipoLiquidacion: Int? = null,
    @Column("id_tipocomprobante")
    @JsonProperty("documentTypeId")
    val idTipoComprobante: Int? = null,
    @Column("id_sustentotributario")
    @JsonProperty("taxSupportId")
    val idSustentoTributario: String? = null,
    @Column("numero_facturaproveedor")
    @JsonProperty("invoiceNumberProvider")
    val numeroFacturaProveedor: String? = null,
    @Column("claveacceso_facturaproveedor")
    @JsonProperty("accessKeyProviderInvoice")
    val claveAccesoFacturaProveedor: String? = null,
    @Column("numeroautorizacion_facproveedor")
    @JsonProperty("authorizationNumberProviderInvoice")
    val numeroAutorizacionFacturaProveedor: String? = null,
    @Column("fechaemision_facturaproveedor")
    @JsonProperty("issueDateProviderInvoice")
    val fechaEmisionFacturaProveedor: LocalDateTime? = null,
    @Column("fechavencimiento_facturaproveedor")
    @JsonProperty("expirationDateProviderInvoice")
    val fechaVencimientoFacturaProveedor: LocalDateTime? = null,
    @Column("descripcion_facturaproveedor")
    @JsonProperty("descriptionProviderInvoice")
    val descripcionFacturaProveedor: String? = null,
    @Column("tarifaiva0_facproveedor")
    @JsonProperty("vat0RateProviderInvoice")
    val tarifaIva0FacturaProveedor: BigDecimal? = null,
    @Column("tarifaiva0serv_facproveedor")
    @JsonProperty("vat0RateServiceProviderInvoice")
    val tarifaIva0ServicioFacturaProveedor: BigDecimal? = null,
    @Column("tarifanoiva_facproveedor")
    @JsonProperty("nonVatRateProviderInvoice")
    val tarifaNoIvaFacturaProveedor: BigDecimal? = null,
    @Column("tarifanoivaserv_facproveedor")
    @JsonProperty("nonVatRateServiceProviderInvoice")
    val tarifaNoIvaServicioFacturaProveedor: BigDecimal? = null,
    @Column("subtotal_facturaproveedor")
    @JsonProperty("invoiceProviderSubtotal")
    val subtotalFacturaProveedor: BigDecimal? = null,
    @Column("iva_facturaproveedor")
    @JsonProperty("vatProviderInvoice")
    val ivaFacturaProveedor: BigDecimal? = null,
    @Column("subtotal2_facturaproveedor")
    @JsonProperty("invoiceProviderSubtotal2")
    val subtotal2FacturaProveedor: BigDecimal? = null,
    @Column("iva2_facturaproveedor")
    @JsonProperty("vat2ProviderInvoice")
    val iva2FacturaProveedor: BigDecimal? = null,
    @Column("total_facturaproveedor")
    @JsonProperty("totalProviderInvoice")
    val totalFacturaProveedor: BigDecimal? = null,
    @Column("apagar_facturaproveedor")
    @JsonProperty("payProviderInvoice")
    val apagarFacturaProveedor: BigDecimal? = null,
    @Column("retencion_facturaproveedor")
    @JsonProperty("retentionProviderInvoice")
    val retencionFacturaProveedor: BigDecimal? = null,
    @Column("documentos_relacionados")
    @JsonProperty("relatedDocuments")
    val documentosRelacionados: String? = null,
    @Column("motivoanulacion_facturaproveedor")
    @JsonProperty("reasonCancellationProviderInvoice")
    val motivoAnulacionFacturaProveedor: String? = null,
    @Column("codimportacion_facproveedor")
    @JsonProperty("importCodeProviderInvoice")
    val codigoImportacionFacturaProveedor: String? = null,
    @Column("diaspago_facproveedor")
    @JsonProperty("paymentDaysProviderInvoice")
    val diasPagoFacturaProveedor: Int? = null,
    @Column("desc_facproveedor")
    @JsonProperty("discountProviderInvoice")
    val descFacturaProveedor: BigDecimal? = null,
    @Column("espagocajachica_facproveedor")
    @JsonProperty("isCashPaymentProviderInvoice")
    val esPagoCajaChicaFacturaProveedor: Boolean? = null,
    @Column("escrucecontable_facproveedor")
    @JsonProperty("isAccountMatchingProviderInvoice")
    val esCruceContableFacturaProveedor: Boolean? = null,
    @Column("tarifaiva_facproveedor")
    @JsonProperty("vatRateProviderInvoice")
    val tarifaIvaFacturaProveedor: BigDecimal? = null,
    @Column("tarifaivaserv_facproveedor")
    @JsonProperty("vatRateServiceProviderInvoice")
    val tarifaIvaServicioFacturaProveedor: BigDecimal? = null,
    @Column("xmlliquida_facproveedor")
    @JsonProperty("liquidationXmlProviderInvoice")
    val xmlLiquidacionFacturaProveedor: String? = null,
    @Column("fecharegistro_facproveedor")
    @JsonProperty("registerDateProviderInvoice")
    val fechaRegistroFacturaProveedor: LocalDateTime? = null,
    @Column("ivaservct_facproveedor")
    @JsonProperty("serviceIvaCtProviderInvoice")
    val ivaServicioCtFacturaProveedor: BigDecimal? = null,
    @Column("id_tipodoc")
    @JsonProperty("documentTypeId")
    val tipoDocumentoId: Int? = null,
    @Column("cod332_facproveedor")
    @JsonProperty("code332ProviderInvoice")
    val codigo332FacturaProveedor: BigDecimal? = null,
    @Column("saldo_facturaproveedor")
    @JsonProperty("providerInvoiceBalance")
    val saldoFacturaProveedor: BigDecimal? = null,
    @Column("codasientoimpo_facproveedor")
    @JsonProperty("importVoucherCodeProviderInvoice")
    val codigoAsientoImportacionFacturaProveedor: Int? = null,
    @Column("coddoc_facproveedor")
    @JsonProperty("documentCodeProviderInvoice")
    val codigoDocumentoFacturaProveedor: Int? = null,
    @Column("autorizado_facproveedor")
    @JsonProperty("authorizedProviderInvoice")
    val autorizadoFacturaProveedor: Boolean? = null,
)
