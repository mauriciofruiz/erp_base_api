package erp.base.api.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;


@Table("FACTURAPROVEEDOR")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdqFacturaProveedor {
    @Id
    @Column("id_facturaproveedor")
    @JsonProperty("invoiceProviderId")
    private Integer idFacturaProveedor;
    @Column("id_asiento")
    @JsonProperty("seatId")
    private Integer idAsiento;
    @Column("id_formapagocobro")
    @JsonProperty("paymentMethodId")
    private String idFormaPagoCobro;
    @Column("id_proveedor")
    @JsonProperty("providerId")
    private Integer idProveedor;
    @Column("id_usr")
    @JsonProperty("userId")
    private Integer idUsuario;
    @Column("id_condicionpago")
    @JsonProperty("paymentConditionId")
    private Integer idCondicionPago;
    @Column("id_tipopago")
    @JsonProperty("paymentTypeId")
    private Integer idTipoPago;
    @Column("id_estadofacprov")
    @JsonProperty("invoiceProviderStateId")
    private Integer idEstadoFacturaProveedor;
    @Column("id_sucursal")
    @JsonProperty("branchId")
    private Integer idSucursal;
    @Column("id_tipoliquidacion")
    @JsonProperty("liquidationTypeId")
    private Integer idTipoLiquidacion;
    @Column("id_tipocomprobante")
    @JsonProperty("documentTypeId")
    private Integer idTipoComprobante;
    @Column("id_sustentotributario")
    @JsonProperty("taxSupportId")
    private String idSustentoTributario;
    @Column("numero_facturaproveedor")
    @JsonProperty("invoiceNumberProvider")
    private String numeroFacturaProveedor;
    @Column("claveacceso_facturaproveedor")
    @JsonProperty("accessKeyProviderInvoice")
    private String claveAccesoFacturaProveedor;
    @Column("numeroautorizacion_facproveedor")
    @JsonProperty("authorizationNumberProviderInvoice")
    private String numeroAutorizacionFacturaProveedor;
    @Column("fechaemision_facturaproveedor")
    @JsonProperty("issueDateProviderInvoice")
    private LocalDateTime fechaEmisionFacturaProveedor;
    @Column("fechavencimiento_facturaproveedor")
    @JsonProperty("expirationDateProviderInvoice")
    private LocalDateTime fechaVencimientoFacturaProveedor;
    @Column("descripcion_facturaproveedor")
    @JsonProperty("descriptionProviderInvoice")
    private String descripcionFacturaProveedor;
    @Column("tarifaiva0_facproveedor")
    @JsonProperty("vat0RateProviderInvoice")
    private BigDecimal tarifaIva0FacturaProveedor;
    @Column("tarifaiva0serv_facproveedor")
    @JsonProperty("vat0RateServiceProviderInvoice")
    private BigDecimal tarifaIva0ServicioFacturaProveedor;
    @Column("tarifanoiva_facproveedor")
    @JsonProperty("nonVatRateProviderInvoice")
    private BigDecimal tarifaNoIvaFacturaProveedor;
    @Column("tarifanoivaserv_facproveedor")
    @JsonProperty("nonVatRateServiceProviderInvoice")
    private BigDecimal tarifaNoIvaServicioFacturaProveedor;
    @Column("subtotal_facturaproveedor")
    @JsonProperty("invoiceProviderSubtotal")
    private BigDecimal subtotalFacturaProveedor;
    @Column("iva_facturaproveedor")
    @JsonProperty("vatProviderInvoice")
    private BigDecimal ivaFacturaProveedor;
    @Column("subtotal2_facturaproveedor")
    @JsonProperty("invoiceProviderSubtotal2")
    private BigDecimal subtotal2FacturaProveedor;
    @Column("iva2_facturaproveedor")
    @JsonProperty("vat2ProviderInvoice")
    private BigDecimal iva2FacturaProveedor;
    @Column("total_facturaproveedor")
    @JsonProperty("totalProviderInvoice")
    private BigDecimal totalFacturaProveedor;
    @Column("apagar_facturaproveedor")
    @JsonProperty("payProviderInvoice")
    private BigDecimal apagarFacturaProveedor;
    @Column("retencion_facturaproveedor")
    @JsonProperty("retentionProviderInvoice")
    private BigDecimal retencionFacturaProveedor;
    @Column("documentos_relacionados")
    @JsonProperty("relatedDocuments")
    private String documentosRelacionados;
    @Column("motivoanulacion_facturaproveedor")
    @JsonProperty("reasonCancellationProviderInvoice")
    private String motivoAnulacionFacturaProveedor;
    @Column("codimportacion_facproveedor")
    @JsonProperty("importCodeProviderInvoice")
    private String codigoImportacionFacturaProveedor;
    @Column("diaspago_facproveedor")
    @JsonProperty("paymentDaysProviderInvoice")
    private Integer diasPagoFacturaProveedor;
    @Column("desc_facproveedor")
    @JsonProperty("discountProviderInvoice")
    private BigDecimal descFacturaProveedor;
    @Column("espagocajachica_facproveedor")
    @JsonProperty("isCashPaymentProviderInvoice")
    private Boolean esPagoCajaChicaFacturaProveedor;
    @Column("escrucecontable_facproveedor")
    @JsonProperty("isAccountMatchingProviderInvoice")
    private Boolean esCruceContableFacturaProveedor;
    @Column("tarifaiva_facproveedor")
    @JsonProperty("vatRateProviderInvoice")
    private BigDecimal tarifaIvaFacturaProveedor;
    @Column("tarifaivaserv_facproveedor")
    @JsonProperty("vatRateServiceProviderInvoice")
    private BigDecimal tarifaIvaServicioFacturaProveedor;
    @Column("xmlliquida_facproveedor")
    @JsonProperty("liquidationXmlProviderInvoice")
    private String xmlLiquidacionFacturaProveedor;
    @Column("fecharegistro_facproveedor")
    @JsonProperty("registerDateProviderInvoice")
    private LocalDateTime fechaRegistroFacturaProveedor;
    @Column("ivaservct_facproveedor")
    @JsonProperty("serviceIvaCtProviderInvoice")
    private BigDecimal ivaServicioCtFacturaProveedor;
    @Column("id_tipodoc")
    @JsonProperty("documentTypeId")
    private Integer tipoDocumentoId;
    @Column("cod332_facproveedor")
    @JsonProperty("code332ProviderInvoice")
    private BigDecimal codigo332FacturaProveedor;
    @Column("saldo_facturaproveedor")
    @JsonProperty("providerInvoiceBalance")
    private BigDecimal saldoFacturaProveedor;
    @Column("codasientoimpo_facproveedor")
    @JsonProperty("importVoucherCodeProviderInvoice")
    private Integer codigoAsientoImportacionFacturaProveedor;
    @Column("coddoc_facproveedor")
    @JsonProperty("documentCodeProviderInvoice")
    private Integer codigoDocumentoFacturaProveedor;
    @Column("autorizado_facproveedor")
    @JsonProperty("authorizedProviderInvoice")
    private Boolean autorizadoFacturaProveedor;
}
