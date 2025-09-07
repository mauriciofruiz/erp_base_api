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
import java.time.ZonedDateTime;

@Table("DOCUMENTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Documento extends BaseEntity {

    @Id
    @Column("doc_id_documento")
    @JsonProperty("documentId")
    private Long id;

    @Column("doc_id_tipocomprobante")
    @JsonProperty("documentType")
    private String idTipoComprobante;

    @Column("doc_id_tipoidentificacionfiscal")
    @JsonProperty("fiscalIdentificationType")
    private String idTipoIdentificacionFiscal;

    @Column("doc_id_estadodocumento")
    @JsonProperty("documentStateId")
    private Integer idEstadoDocumento;

    @Column("doc_id_asiento")
    @JsonProperty("entryId")
    private Integer idAsiento;

    @Column("doc_cod_sucursal")
    @JsonProperty("branchCode")
    private String codSucursal;

    @Column("doc_punto_emision")
    @JsonProperty("emissionPoint")
    private String puntoEmision;

    @Column("doc_secuencial")
    @JsonProperty("sequential")
    private String secuencial;

    @Column("doc_id_formapagocobro")
    @JsonProperty("paymentMethodId")
    private String idFormaPagoCobro;

    @Column("doc_id_condicionpago")
    @JsonProperty("paymentConditionId")
    private Integer idCondicionPago;

    @Column("doc_id_tipopago")
    @JsonProperty("paymentTypeId")
    private Integer idTipoPago;

    @Column("doc_id_tipoliquidacion")
    @JsonProperty("liquidationTypeId")
    private Integer idTipoLiquidacion;

    @Column("doc_id_sustentotributario")
    @JsonProperty("taxSupportId")
    private String idSustentoTributario;

    @Column("doc_ambiente")
    @JsonProperty("environment")
    private Integer ambiente;

    @Column("doc_conceptodocumento")
    @JsonProperty("documentConcept")
    private String conceptoDocumento;

    @Column("doc_tipoemision")
    @JsonProperty("emissionType")
    private Integer tipoEmision;

    @Column("doc_numerodocumento")
    @JsonProperty("documentNumber")
    private String numeroDocumento;

    @Column("doc_claveacceso")
    @JsonProperty("accessKey")
    private String claveAcceso;

    @Column("doc_razonsocial_emisor")
    @JsonProperty("issuerBusinessName")
    private String razonSocialEmisor;

    @Column("doc_nombrecomercial_emisor")
    @JsonProperty("issuerCommercialName")
    private String nombreComercialEmisor;

    @Column("doc_identificacion_emisor")
    @JsonProperty("issuerIdentification")
    private String identificacionEmisor;

    @Column("doc_direccion_matriz")
    @JsonProperty("headquartersAddress")
    private String direccionMatriz;

    @Column("doc_fechaemision")
    @JsonProperty("emissionDate")
    private ZonedDateTime fechaEmision;

    @Column("doc_fechavencimiento")
    @JsonProperty("expirationDate")
    private ZonedDateTime fechaVencimiento;

    @Column("doc_direccion_sucursal")
    @JsonProperty("branchAddress")
    private String direccionSucursal;

    @Column("doc_contribuyenteespecial")
    @JsonProperty("specialContributor")
    private String contribuyenteEspecial;

    @Column("doc_obligadocontabilidad")
    @JsonProperty("accountingObligated")
    private Boolean obligadoContabilidad;

    @Column("doc_identificacion")
    @JsonProperty("identification")
    private String identificacion;

    @Column("doc_razonsocial")
    @JsonProperty("businessName")
    private String razonSocial;

    @Column("doc_correo")
    @JsonProperty("email")
    private String correo;

    @Column("doc_direccion")
    @JsonProperty("address")
    private String direccion;

    @Column("doc_telefono")
    @JsonProperty("phone")
    private String telefono;

    @Column("doc_placa")
    @JsonProperty("plate")
    private String placa;

    @Column("doc_agenteretencion")
    @JsonProperty("retentionAgent")
    private Integer agenteRetencion;

    @Column("doc_guiaremision")
    @JsonProperty("remissionGuide")
    private String guiaRemision;

    @Builder.Default
    @Column("doc_noobjetoiva")
    @JsonProperty("noVatObject")
    private BigDecimal noObjetoIva = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_totalsiniva")
    @JsonProperty("totalWithoutVat")
    private BigDecimal totalSinIva  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_totalconiva")
    @JsonProperty("totalWithVat")
    private BigDecimal totalConIva  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_totaldescuento")
    @JsonProperty("totalDiscount")
    private BigDecimal totalDescuento  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_montoiva")
    @JsonProperty("vatAmount")
    private BigDecimal montoIva  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_valor")
    @JsonProperty("value")
    private BigDecimal valor  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_valor_nc")
    @JsonProperty("ncValue")
    private BigDecimal valorNc  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_valor_nd")
    @JsonProperty("ndValue")
    private BigDecimal valorNd  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_valor_retencion")
    @JsonProperty("retentionValue")
    private BigDecimal valorRetencion  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_valor_apagar")
    @JsonProperty("valueToPay")
    private BigDecimal valorApagar  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_saldo")
    @JsonProperty("balance")
    private BigDecimal saldo  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_valorflete")
    @JsonProperty("freightValue")
    private BigDecimal valorFlete  = BigDecimal.ZERO;

    @Column("doc_id_aperturaimportacion")
    @JsonProperty("importOpeningId")
    private Integer idAperturaImportacion;

    @Column("doc_id_estadoentrega")
    @JsonProperty("deliveryStateId")
    private Integer idEstadoEntrega;

    @Column("doc_id_anticipotransporte")
    @JsonProperty("transportAdvanceId")
    private Integer idAnticipoTransporte;

    @Column("doc_id_aperturacaja")
    @JsonProperty("cashOpeningId")
    private Long idAperturaCaja;

    @Column("doc_id_cotizacion")
    @JsonProperty("quotationId")
    private Long idCotizacion;

    @Builder.Default
    @Column("doc_valorcredito")
    @JsonProperty("creditValue")
    private BigDecimal valorCredito  = BigDecimal.ZERO;

    @Column("doc_numerocuotas")
    @JsonProperty("installmentNumber")
    private Integer numeroCuotas;

    @Column("doc_fechaanulacion")
    @JsonProperty("cancellationDate")
    private ZonedDateTime fechaAnulacion;

    @Column("doc_motivoanula")
    @JsonProperty("cancellationReason")
    private String motivoAnula;

    @Column("doc_fechaautorizacion")
    @JsonProperty("authorizationDate")
    private ZonedDateTime fechaAutorizacion;

    @Column("doc_estadocomprobantesri")
    @JsonProperty("sriDocumentState")
    private String estadoComprobanteSri;

    @Column("doc_errorautorizacion")
    @JsonProperty("authorizationError")
    private Integer errorAutorizacion;

    @Column("doc_puntopartida")
    @JsonProperty("startingPoint")
    private String puntoPartida;

    @Column("doc_fechainicio")
    @JsonProperty("startDate")
    private LocalDateTime fechaInicio;

    @Column("doc_fechafin")
    @JsonProperty("endDate")
    private LocalDateTime fechaFin;

    @Column("doc_motivotraslado")
    @JsonProperty("transferReason")
    private String motivoTraslado;

    @Column("doc_destino")
    @JsonProperty("destination")
    private String destino;

    @Column("doc_documentoaduanero")
    @JsonProperty("customsDocument")
    private String documentoAduanero;

    @Column("doc_establecimientodestino")
    @JsonProperty("destinationEstablishment")
    private String establecimientoDestino;

    @Column("doc_ruta")
    @JsonProperty("route")
    private String ruta;

    @Column("doc_plazo")
    @JsonProperty("term")
    private Integer plazo;

    @Column("doc_iddocreembolso")
    @JsonProperty("reimbursementDocumentId")
    private Integer idDocReembolso;

    @Column("doc_sinimpuestosliq")
    @JsonProperty("liquidationWithoutTaxes")
    private BigDecimal sinImpuestosLiq;

    @Column("doc_totaldescuentoliq")
    @JsonProperty("liquidationTotalDiscount")
    private BigDecimal totalDescuentoLiq;

    @Column("doc_totalcomprobantereembolso")
    @JsonProperty("reimbursementDocumentTotal")
    private BigDecimal totalComprobanteReembolso;

    @Column("doc_baseimponiblereembolso")
    @JsonProperty("reimbursementTaxableBase")
    private BigDecimal baseImponibleReembolso;

    @Column("doc_totalimpuestoreembolso")
    @JsonProperty("reimbursementTotalTax")
    private BigDecimal totalImpuestoReembolso;

    @Column("doc_campoadicionalemailcliente")
    @JsonProperty("additionalFieldEmailClient")
    private String campoAdicionalEmailCliente;

    @Column("doc_campoadicionaldircliente")
    @JsonProperty("additionalFieldAddressClient")
    private String campoAdicionalDirCliente;

    @Column("doc_campoadicionaltelcliente")
    @JsonProperty("additionalFieldPhoneClient")
    private String campoAdicionalTelCliente;

    @Column("doc_campoadicionalnumerocliente")
    @JsonProperty("additionalFieldNumberClient")
    private String campoAdicionalNumeroCliente;

    @Column("doc_id_retencion")
    @JsonProperty("retentionId")
    private Long idRetencion;

    @Column("doc_id_documento_relacionado")
    @JsonProperty("relatedDocumentId")
    private Long idDocumentoRelacionado;

    @Column("doc_id_documento_relacionado2")
    @JsonProperty("relatedDocumentId2")
    private Long idDocumentoRelacionado2;

    @Column("doc_hospedado")
    @JsonProperty("hosted")
    private Boolean hospedado;

    @Column("doc_idproveedor")
    @JsonProperty("providerId")
    private Integer idProveedor;

    @Column("doc_idcliente")
    @JsonProperty("clientId")
    private Long idCliente;

    @Column("doc_host")
    @JsonProperty("host")
    private String host;

    @Column("doc_sucursal")
    @JsonProperty("branch")
    private String nombreSucursal;

    @Column("doc_id_sucursal")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("id_asiento_reserva")
    @JsonProperty("reserveEntryId")
    private Integer idAsientoReserva;

    @Column("id_asiento_extra")
    @JsonProperty("extraEntryId")
    private Integer idAsientoExtra;

    @Column("id_cuenta_tarjeta")
    @JsonProperty("cardAccountId")
    private Integer idCuentaTarjeta;

    @Column("asignado_liquidacion")
    @JsonProperty("assignedLiquidation")
    private Boolean asignadoLiquidacion;

    @Column("id_tipo_nc")
    @JsonProperty("ncTypeId")
    private Integer idTipoNotaCredito;

    @Builder.Default
    @Column("valor_pagado")
    @JsonProperty("paidValue")
    private BigDecimal valorPagado  = BigDecimal.ZERO;

    @Builder.Default
    @Column("doc_porcentaje_descuento")
    @JsonProperty("discountPercentage")
    private BigDecimal porcentajeDescuento  = BigDecimal.ZERO;

    @Builder.Default
    @Column("valor_cruzado")
    @JsonProperty("crossedValue")
    private BigDecimal valorCruzado  = BigDecimal.ZERO;

    @Column("doc_id_caja")
    @JsonProperty("cashId")
    private Integer idCaja;

    @Builder.Default
    @Column("doc_tiene_credito")
    @JsonProperty("hasCredit")
    private Boolean tieneCredito = Boolean.FALSE;

    @Column("doc_id_categoria_cliente")
    @JsonProperty("clientCategoryId")
    private Integer idCategoriaCliente;

    @Column("es_caja_chica")
    @JsonProperty("isPettyCash")
    private Boolean esCajaChica;

    @Builder.Default
    @Column("is_enviado_correo")
    @JsonProperty("isEmailSent")
    private Boolean isEnviadoCorreo= Boolean.FALSE;
}
