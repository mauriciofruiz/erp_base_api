package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Table("FACTURA")
data class Factura(
    @Id
    @Column("ID_FACTURA")
    @JsonProperty("invoiceId")
    val idFactura: Int,
    @Column("ID_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceId")
    val idAnticipoTransporte: Int? = null,
    @Column("ID_ESTADOENTREGA")
    @JsonProperty("deliveryStatusId")
    val idEstadoEntrega: Int? = null,
    @Column("ID_CAJA")
    @JsonProperty("cashBoxId")
    val idCaja: Int? = null,
    @Column("ID_APERTURACAJA")
    @JsonProperty("cashDrawerOpeningId")
    val idAperturaCaja: Int? = null,
    @Column("ID_CLIENTE")
    @JsonProperty("clientId")
    val idCliente: Int? = null,
    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
    @Column("ID_ASIENTO")
    @JsonProperty("seatId")
    val idAsiento: Int? = null,
    @Column("ID_TIPOCOMPROBANTE")
    @JsonProperty("voucherTypeId")
    val idTipoComprobante: Int? = null,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsr: Int? = null,
    @Column("ID_SUSTENTOTRIBUTARIO")
    @JsonProperty("taxSupportId")
    val idSustentoTributario: String? = null,
    @Column("ID_ESTADOFACTURA")
    @JsonProperty("invoiceStatusId")
    val idEstadoFactura: Int? = null,
    @Column("NUMERO_FACTURA")
    @JsonProperty("invoiceNumber")
    val numeroFactura: String? = null,
    @Column("NUMEROGLOBAL_FACTURA")
    @JsonProperty("globalInvoiceNumber")
    val numeroGlobalFactura: String? = null,
    @Column("SUBTOTAL_FACTURA")
    @JsonProperty("invoiceSubtotal")
    val subtotalFactura: BigDecimal? = null,
    @Column("SUBTOTALNOOBJETOIVA_FACTURA")
    @JsonProperty("invoiceNonTaxableSubtotal")
    val subtotalNoObjetoIvaFactura: BigDecimal? = null,
    @Column("SUBTOTALEXENTOIVA_FACTURA")
    @JsonProperty("invoiceExemptSubtotal")
    val subtotalExentoIvaFactura: BigDecimal? = null,
    @Column("PORCDESCUENTO_FACTURA")
    @JsonProperty("invoiceDiscountPercentage")
    val porcentajeDescuentoFactura: BigDecimal? = null,
    @Column("DESCUENTO_FACTURA")
    @JsonProperty("invoiceDiscount")
    val descuentoFactura: BigDecimal? = null,
    @Column("PORCDESCCLIENTE_FACTURA")
    @JsonProperty("customerDiscountPercentage")
    val porcentajeDescuentoClienteFactura: BigDecimal? = null,
    @Column("DESCCLIENTE_FACTURA")
    @JsonProperty("customerDiscount")
    val descuentoClienteFactura: BigDecimal? = null,
    @Column("PORCDESCESPECIAL_FACTURA")
    @JsonProperty("specialDiscountPercentage")
    val porcentajeDescuentoEspecialFactura: BigDecimal? = null,
    @Column("DESCESPECIAL_FACTURA")
    @JsonProperty("specialDiscount")
    val descuentoEspecialFactura: BigDecimal? = null,
    @Column("PORCPROMOCION_FACTURA")
    @JsonProperty("promotionPercentage")
    val porcentajePromocionFactura: BigDecimal? = null,
    @Column("DESCPROMOCION_FACTURA")
    @JsonProperty("promotionDiscount")
    val descuentoPromocionFactura: BigDecimal? = null,
    @Column("PORCDESCCONTA_FACTURA")
    @JsonProperty("accountingDiscountPercentage")
    val porcentajeDescuentoContaFactura: BigDecimal? = null,
    @Column("DESCCONTA_FACTURA")
    @JsonProperty("accountingDiscount")
    val descuentoContaFactura: BigDecimal? = null,
    @Column("VENTANETA_FACTURA")
    @JsonProperty("netSale")
    val ventaNetaFactura: BigDecimal? = null,
    @Column("TARIFACERO_FACTURA")
    @JsonProperty("zeroRateFee")
    val tarifaCeroFactura: BigDecimal? = null,
    @Column("VENTATARIFAIVA_FACTURA")
    @JsonProperty("taxedRateSale")
    val ventaTarifaIvaFactura: BigDecimal? = null,
    @Column("VALORRET_FACTURA")
    @JsonProperty("withholdingValue")
    val valorRetFactura: BigDecimal? = null,
    @Column("IVA_FACTURA")
    @JsonProperty("vat")
    val ivaFactura: BigDecimal? = null,
    @Column("TOTAL_FACTURA")
    @JsonProperty("invoiceTotal")
    val totalFactura: BigDecimal? = null,
    @Column("VALPAGAR_FACTURA")
    @JsonProperty("payableValue")
    val valorPagarFactura: BigDecimal? = null,
    @Column("FECHAEMISION_FACTURA")
    @JsonProperty("issueDate")
    val fechaEmisionFactura: LocalDateTime? = null,
    @Column("NUMEROAUTORIZACION_FACTURA")
    @JsonProperty("authorizationNumber")
    val numeroAutorizacionFactura: String? = null,
    @Column("CLAVEACCESO_FACTURA")
    @JsonProperty("accessKey")
    val claveAccesoFactura: String? = null,
    @Column("FECHAAUTORIZACION_FACTURA")
    @JsonProperty("authorizationDate")
    val fechaAutorizacionFactura: String? = null,
    @Column("SALDO_FACTURA")
    @JsonProperty("invoiceBalance")
    val saldoFactura: BigDecimal? = null,
    @Column("DIASCREDITO_FACTURA")
    @JsonProperty("creditDays")
    val diasCreditoFactura: Int? = null,
    @Column("FECHAVENCIMIENTO_FACTURA")
    @JsonProperty("dueDate")
    val fechaVencimientoFactura: LocalDate? = null,
    @Column("OBS_FACTURA")
    @JsonProperty("invoiceObservation")
    val obsFactura: String? = null,
    @Column("XRECEPCIONAR")
    @JsonProperty("toBeReceived")
    val recepcionar: Boolean? = null,
    @Column("COD_COTIZACION")
    @JsonProperty("quotationCode")
    val codCotizacion: String? = null,
    @Column("COD_RETENCION")
    @JsonProperty("retentionCode")
    val codRetencion: String? = null,
    @Column("XML_FACTURA")
    @JsonProperty("invoiceXml")
    val xmlFactura: String? = null,
    @Column("ENVIADACORREO_FACTURA")
    @JsonProperty("sentByEmail")
    val enviadaCorreoFactura: Boolean? = null,
    @Column("ESSERVICIO_FACTURA")
    @JsonProperty("isServiceInvoice")
    val esServicioFactura: Boolean? = null,
    @Column("ANULADA_FACTURA")
    @JsonProperty("isCancelledInvoice")
    val anuladaFactura: Boolean? = null,
    @Column("VALORND_FACTURA")
    @JsonProperty("creditNoteValue")
    val valorNotaDebitoFactura: BigDecimal? = null,
    @Column("ID_TIPODOC")
    @JsonProperty("documentTypeId")
    val idTipoDoc: Int? = null,
    @Column("ESCREDITO_FACTURA")
    @JsonProperty("isCreditInvoice")
    val esCreditoFactura: Boolean? = null,
    @Column("VALORCREDITO_FACTURA")
    @JsonProperty("creditValue")
    val valorCreditoFactura: BigDecimal? = null,
    @Column("ACUOTAS_FACTURA")
    @JsonProperty("hasInstallments")
    val acuotasFactura: Boolean? = null,
    @Column("DESCPROMESPECIAL_FACTURA")
    @JsonProperty("specialPromotionDiscount")
    val descPromocionEspecialFactura: BigDecimal? = null,
)
