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

@Table("VTS_FACTURA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VtsFactura {
    @Id
    @Column("ID_FACTURA")
    @JsonProperty("invoiceId")
    private Integer idFactura;

    @Column("ID_ANTICIPOTRANSPORTE")
    @JsonProperty("transportAdvanceId")
    private Integer idAnticipoTransporte;

    @Column("ID_ESTADOENTREGA")
    @JsonProperty("deliveryStatusId")
    private Integer idEstadoEntrega;

    @Column("ID_CAJA")
    @JsonProperty("cashBoxId")
    private Integer idCaja;

    @Column("ID_APERTURACAJA")
    @JsonProperty("cashDrawerOpeningId")
    private Integer idAperturaCaja;

    @Column("ID_CLIENTE")
    @JsonProperty("clientId")
    private Integer idCliente;

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("ID_ASIENTO")
    @JsonProperty("seatId")
    private Integer idAsiento;

    @Column("ID_TIPOCOMPROBANTE")
    @JsonProperty("voucherTypeId")
    private Integer idTipoComprobante;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsr;

    @Column("ID_SUSTENTOTRIBUTARIO")
    @JsonProperty("taxSupportId")
    private String idSustentoTributario;

    @Column("ID_ESTADOFACTURA")
    @JsonProperty("invoiceStatusId")
    private Integer idEstadoFactura;

    @Column("NUMERO_FACTURA")
    @JsonProperty("invoiceNumber")
    private String numeroFactura;

    @Column("NUMEROGLOBAL_FACTURA")
    @JsonProperty("globalInvoiceNumber")
    private String numeroGlobalFactura;

    @Column("SUBTOTAL_FACTURA")
    @JsonProperty("invoiceSubtotal")
    private BigDecimal subtotalFactura;

    @Column("SUBTOTALNOOBJETOIVA_FACTURA")
    @JsonProperty("invoiceNonTaxableSubtotal")
    private BigDecimal subtotalNoObjetoIvaFactura;

    @Column("SUBTOTALEXENTOIVA_FACTURA")
    @JsonProperty("invoiceExemptSubtotal")
    private BigDecimal subtotalExentoIvaFactura;

    @Column("PORCDESCUENTO_FACTURA")
    @JsonProperty("invoiceDiscountPercentage")
    private BigDecimal porcentajeDescuentoFactura;

    @Column("DESCUENTO_FACTURA")
    @JsonProperty("invoiceDiscount")
    private BigDecimal descuentoFactura;

    @Column("PORCDESCCLIENTE_FACTURA")
    @JsonProperty("customerDiscountPercentage")
    private BigDecimal porcentajeDescuentoClienteFactura;

    @Column("DESCCLIENTE_FACTURA")
    @JsonProperty("customerDiscount")
    private BigDecimal descuentoClienteFactura;

    @Column("PORCDESCESPECIAL_FACTURA")
    @JsonProperty("specialDiscountPercentage")
    private BigDecimal porcentajeDescuentoEspecialFactura;

    @Column("DESCESPECIAL_FACTURA")
    @JsonProperty("specialDiscount")
    private BigDecimal descuentoEspecialFactura;

    @Column("PORCPROMOCION_FACTURA")
    @JsonProperty("promotionPercentage")
    private BigDecimal porcentajePromocionFactura;

    @Column("DESCPROMOCION_FACTURA")
    @JsonProperty("promotionDiscount")
    private BigDecimal descuentoPromocionFactura;

    @Column("PORCDESCCONTA_FACTURA")
    @JsonProperty("accountingDiscountPercentage")
    private BigDecimal porcentajeDescuentoContaFactura;

    @Column("DESCCONTA_FACTURA")
    @JsonProperty("accountingDiscount")
    private BigDecimal descuentoContaFactura;

    @Column("VENTANETA_FACTURA")
    @JsonProperty("netSale")
    private BigDecimal ventaNetaFactura;

    @Column("TARIFACERO_FACTURA")
    @JsonProperty("zeroRateFee")
    private BigDecimal tarifaCeroFactura;

    @Column("VENTATARIFAIVA_FACTURA")
    @JsonProperty("taxedRateSale")
    private BigDecimal ventaTarifaIvaFactura;

    @Column("VALORRET_FACTURA")
    @JsonProperty("withholdingValue")
    private BigDecimal valorRetFactura;

    @Column("IVA_FACTURA")
    @JsonProperty("vat")
    private BigDecimal ivaFactura;

    @Column("TOTAL_FACTURA")
    @JsonProperty("invoiceTotal")
    private BigDecimal totalFactura;

    @Column("VALPAGAR_FACTURA")
    @JsonProperty("payableValue")
    private BigDecimal valorPagarFactura;

    @Column("FECHAEMISION_FACTURA")
    @JsonProperty("issueDate")
    private LocalDateTime fechaEmisionFactura;

    @Column("NUMEROAUTORIZACION_FACTURA")
    @JsonProperty("authorizationNumber")
    private String numeroAutorizacionFactura;

    @Column("CLAVEACCESO_FACTURA")
    @JsonProperty("accessKey")
    private String claveAccesoFactura;

    @Column("FECHAAUTORIZACION_FACTURA")
    @JsonProperty("authorizationDate")
    private String fechaAutorizacionFactura;

    @Column("SALDO_FACTURA")
    @JsonProperty("invoiceBalance")
    private BigDecimal saldoFactura;

    @Column("DIASCREDITO_FACTURA")
    @JsonProperty("creditDays")
    private Integer diasCreditoFactura;

    @Column("FECHAVENCIMIENTO_FACTURA")
    @JsonProperty("dueDate")
    private LocalDate fechaVencimientoFactura;

    @Column("OBS_FACTURA")
    @JsonProperty("invoiceObservation")
    private String obsFactura;

    @Column("XRECEPCIONAR")
    @JsonProperty("toBeReceived")
    private Boolean recepcionar;

    @Column("COD_COTIZACION")
    @JsonProperty("quotationCode")
    private String codCotizacion;

    @Column("COD_RETENCION")
    @JsonProperty("retentionCode")
    private String codRetencion;

    @Column("XML_FACTURA")
    @JsonProperty("invoiceXml")
    private String xmlFactura;

    @Column("ENVIADACORREO_FACTURA")
    @JsonProperty("sentByEmail")
    private Boolean enviadaCorreoFactura;

    @Column("ESSERVICIO_FACTURA")
    @JsonProperty("isServiceInvoice")
    private Boolean esServicioFactura;

    @Column("ANULADA_FACTURA")
    @JsonProperty("isCancelledInvoice")
    private Boolean anuladaFactura;

    @Column("VALORND_FACTURA")
    @JsonProperty("creditNoteValue")
    private BigDecimal valorNotaDebitoFactura;

    @Column("ID_TIPODOC")
    @JsonProperty("documentTypeId")
    private Integer idTipoDoc;

    @Column("ESCREDITO_FACTURA")
    @JsonProperty("isCreditInvoice")
    private Boolean esCreditoFactura;

    @Column("VALORCREDITO_FACTURA")
    @JsonProperty("creditValue")
    private BigDecimal valorCreditoFactura;

    @Column("ACUOTAS_FACTURA")
    @JsonProperty("hasInstallments")
    private Boolean acuotasFactura;

    @Column("DESCPROMESPECIAL_FACTURA")
    @JsonProperty("specialPromotionDiscount")
    private BigDecimal descPromocionEspecialFactura;
}
