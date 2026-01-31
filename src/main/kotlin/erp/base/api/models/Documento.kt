package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Table("DOCUMENTO")
data class Documento(
    @Id
    @Column("doc_id_documento")
    @JsonProperty("documentId")
    val id: Long,
    @Column("doc_id_tipocomprobante")
    @JsonProperty("documentType")
    val idTipoComprobante: String? = null,
    @Column("doc_id_tipoidentificacionfiscal")
    @JsonProperty("fiscalIdentificationType")
    val idTipoIdentificacionFiscal: String? = null,
    @Column("doc_id_estadodocumento")
    @JsonProperty("documentStateId")
    val idEstadoDocumento: Int? = null,
    @Column("doc_id_asiento")
    @JsonProperty("entryId")
    val idAsiento: Int? = null,
    @Column("doc_cod_sucursal")
    @JsonProperty("branchCode")
    val codSucursal: String? = null,
    @Column("doc_punto_emision")
    @JsonProperty("emissionPoint")
    val puntoEmision: String? = null,
    @Column("doc_secuencial")
    @JsonProperty("sequential")
    val secuencial: String? = null,
    @Column("doc_id_formapagocobro")
    @JsonProperty("paymentMethodId")
    val idFormaPagoCobro: String? = null,
    @Column("doc_id_condicionpago")
    @JsonProperty("paymentConditionId")
    val idCondicionPago: Int? = null,
    @Column("doc_id_tipopago")
    @JsonProperty("paymentTypeId")
    val idTipoPago: Int? = null,
    @Column("doc_id_tipoliquidacion")
    @JsonProperty("liquidationTypeId")
    val idTipoLiquidacion: Int? = null,
    @Column("doc_id_sustentotributario")
    @JsonProperty("taxSupportId")
    val idSustentoTributario: String? = null,
    @Column("doc_ambiente")
    @JsonProperty("environment")
    val ambiente: Int? = null,
    @Column("doc_conceptodocumento")
    @JsonProperty("documentConcept")
    val conceptoDocumento: String? = null,
    @Column("doc_tipoemision")
    @JsonProperty("emissionType")
    val tipoEmision: Int? = null,
    @Column("doc_numerodocumento")
    @JsonProperty("documentNumber")
    val numeroDocumento: String? = null,
    @Column("doc_claveacceso")
    @JsonProperty("accessKey")
    val claveAcceso: String? = null,
    @Column("doc_razonsocial_emisor")
    @JsonProperty("issuerBusinessName")
    val razonSocialEmisor: String? = null,
    @Column("doc_nombrecomercial_emisor")
    @JsonProperty("issuerCommercialName")
    val nombreComercialEmisor: String? = null,
    @Column("doc_identificacion_emisor")
    @JsonProperty("issuerIdentification")
    val identificacionEmisor: String? = null,
    @Column("doc_direccion_matriz")
    @JsonProperty("headquartersAddress")
    val direccionMatriz: String? = null,
    @Column("doc_fechaemision")
    @JsonProperty("emissionDate")
    val fechaEmision: ZonedDateTime? = null,
    @Column("doc_fechavencimiento")
    @JsonProperty("expirationDate")
    val fechaVencimiento: ZonedDateTime? = null,
    @Column("doc_direccion_sucursal")
    @JsonProperty("branchAddress")
    val direccionSucursal: String? = null,
    @Column("doc_contribuyenteespecial")
    @JsonProperty("specialContributor")
    val contribuyenteEspecial: String? = null,
    @Column("doc_obligadocontabilidad")
    @JsonProperty("accountingObligated")
    val obligadoContabilidad: Boolean? = null,
    @Column("doc_identificacion")
    @JsonProperty("identification")
    val identificacion: String? = null,
    @Column("doc_razonsocial")
    @JsonProperty("businessName")
    val razonSocial: String? = null,
    @Column("doc_correo")
    @JsonProperty("email")
    val correo: String? = null,
    @Column("doc_direccion")
    @JsonProperty("address")
    val direccion: String? = null,
    @Column("doc_telefono")
    @JsonProperty("phone")
    val telefono: String? = null,
    @Column("doc_placa")
    @JsonProperty("plate")
    val placa: String? = null,
    @Column("doc_agenteretencion")
    @JsonProperty("retentionAgent")
    val agenteRetencion: Int? = null,
    @Column("doc_guiaremision")
    @JsonProperty("remissionGuide")
    val guiaRemision: String? = null,
    @Column("doc_noobjetoiva")
    @JsonProperty("noVatObject")
    val noObjetoIva: BigDecimal = BigDecimal.ZERO,
    @Column("doc_totalsiniva")
    @JsonProperty("totalWithoutVat")
    val totalSinIva: BigDecimal = BigDecimal.ZERO,
    @Column("doc_totalconiva")
    @JsonProperty("totalWithVat")
    val totalConIva: BigDecimal = BigDecimal.ZERO,
    @Column("doc_totaldescuento")
    @JsonProperty("totalDiscount")
    val totalDescuento: BigDecimal = BigDecimal.ZERO,
    @Column("doc_montoiva")
    @JsonProperty("vatAmount")
    val montoIva: BigDecimal = BigDecimal.ZERO,
    @Column("doc_valor")
    @JsonProperty("value")
    val valor: BigDecimal = BigDecimal.ZERO,
    @Column("doc_valor_nc")
    @JsonProperty("ncValue")
    val valorNc: BigDecimal = BigDecimal.ZERO,
    @Column("doc_valor_nd")
    @JsonProperty("ndValue")
    val valorNd: BigDecimal = BigDecimal.ZERO,
    @Column("doc_valor_retencion")
    @JsonProperty("retentionValue")
    val valorRetencion: BigDecimal = BigDecimal.ZERO,
    @Column("doc_valor_apagar")
    @JsonProperty("valueToPay")
    val valorApagar: BigDecimal = BigDecimal.ZERO,
    @Column("doc_saldo")
    @JsonProperty("balance")
    val saldo: BigDecimal = BigDecimal.ZERO,
    @Column("doc_valorflete")
    @JsonProperty("freightValue")
    val valorFlete: BigDecimal = BigDecimal.ZERO,
    @Column("doc_id_aperturaimportacion")
    @JsonProperty("importOpeningId")
    val idAperturaImportacion: Int? = null,
    @Column("doc_id_estadoentrega")
    @JsonProperty("deliveryStateId")
    val idEstadoEntrega: Int? = null,
    @Column("doc_id_anticipotransporte")
    @JsonProperty("transportAdvanceId")
    val idAnticipoTransporte: Int? = null,
    @Column("doc_id_aperturacaja")
    @JsonProperty("cashOpeningId")
    val idAperturaCaja: Long? = null,
    @Column("doc_id_cotizacion")
    @JsonProperty("quotationId")
    val idCotizacion: Long? = null,
    @Column("doc_valorcredito")
    @JsonProperty("creditValue")
    val valorCredito: BigDecimal = BigDecimal.ZERO,
    @Column("doc_numerocuotas")
    @JsonProperty("installmentNumber")
    val numeroCuotas: Int? = null,
    @Column("doc_fechaanulacion")
    @JsonProperty("cancellationDate")
    val fechaAnulacion: ZonedDateTime? = null,
    @Column("doc_motivoanula")
    @JsonProperty("cancellationReason")
    val motivoAnula: String? = null,
    @Column("doc_fechaautorizacion")
    @JsonProperty("authorizationDate")
    val fechaAutorizacion: ZonedDateTime? = null,
    @Column("doc_estadocomprobantesri")
    @JsonProperty("sriDocumentState")
    val estadoComprobanteSri: String? = null,
    @Column("doc_errorautorizacion")
    @JsonProperty("authorizationError")
    val errorAutorizacion: Int? = null,
    @Column("doc_puntopartida")
    @JsonProperty("startingPoint")
    val puntoPartida: String? = null,
    @Column("doc_fechainicio")
    @JsonProperty("startDate")
    val fechaInicio: LocalDateTime? = null,
    @Column("doc_fechafin")
    @JsonProperty("endDate")
    val fechaFin: LocalDateTime? = null,
    @Column("doc_motivotraslado")
    @JsonProperty("transferReason")
    val motivoTraslado: String? = null,
    @Column("doc_destino")
    @JsonProperty("destination")
    val destino: String? = null,
    @Column("doc_documentoaduanero")
    @JsonProperty("customsDocument")
    val documentoAduanero: String? = null,
    @Column("doc_establecimientodestino")
    @JsonProperty("destinationEstablishment")
    val establecimientoDestino: String? = null,
    @Column("doc_ruta")
    @JsonProperty("route")
    val ruta: String? = null,
    @Column("doc_plazo")
    @JsonProperty("term")
    val plazo: Int? = null,
    @Column("doc_iddocreembolso")
    @JsonProperty("reimbursementDocumentId")
    val idDocReembolso: Int? = null,
    @Column("doc_sinimpuestosliq")
    @JsonProperty("liquidationWithoutTaxes")
    val sinImpuestosLiq: BigDecimal? = null,
    @Column("doc_totaldescuentoliq")
    @JsonProperty("liquidationTotalDiscount")
    val totalDescuentoLiq: BigDecimal? = null,
    @Column("doc_totalcomprobantereembolso")
    @JsonProperty("reimbursementDocumentTotal")
    val totalComprobanteReembolso: BigDecimal? = null,
    @Column("doc_baseimponiblereembolso")
    @JsonProperty("reimbursementTaxableBase")
    val baseImponibleReembolso: BigDecimal? = null,
    @Column("doc_totalimpuestoreembolso")
    @JsonProperty("reimbursementTotalTax")
    val totalImpuestoReembolso: BigDecimal? = null,
    @Column("doc_campoadicionalemailcliente")
    @JsonProperty("additionalFieldEmailClient")
    val campoAdicionalEmailCliente: String? = null,
    @Column("doc_campoadicionaldircliente")
    @JsonProperty("additionalFieldAddressClient")
    val campoAdicionalDirCliente: String? = null,
    @Column("doc_campoadicionaltelcliente")
    @JsonProperty("additionalFieldPhoneClient")
    val campoAdicionalTelCliente: String? = null,
    @Column("doc_campoadicionalnumerocliente")
    @JsonProperty("additionalFieldNumberClient")
    val campoAdicionalNumeroCliente: String? = null,
    @Column("doc_id_retencion")
    @JsonProperty("retentionId")
    val idRetencion: Long? = null,
    @Column("doc_id_documento_relacionado")
    @JsonProperty("relatedDocumentId")
    val idDocumentoRelacionado: Long? = null,
    @Column("doc_id_documento_relacionado2")
    @JsonProperty("relatedDocumentId2")
    val idDocumentoRelacionado2: Long? = null,
    @Column("doc_hospedado")
    @JsonProperty("hosted")
    val hospedado: Boolean? = null,
    @Column("doc_idproveedor")
    @JsonProperty("providerId")
    val idProveedor: Int? = null,
    @Column("doc_idcliente")
    @JsonProperty("clientId")
    val idCliente: Long? = null,
    @Column("doc_host")
    @JsonProperty("host")
    val host: String? = null,
    @Column("doc_sucursal")
    @JsonProperty("branch")
    val nombreSucursal: String? = null,
    @Column("doc_id_sucursal")
    @JsonProperty("branchId")
    val idSucursal: Int? = null,
    @Column("id_asiento_reserva")
    @JsonProperty("reserveEntryId")
    val idAsientoReserva: Int? = null,
    @Column("id_asiento_extra")
    @JsonProperty("extraEntryId")
    val idAsientoExtra: Int? = null,
    @Column("id_cuenta_tarjeta")
    @JsonProperty("cardAccountId")
    val idCuentaTarjeta: Int? = null,
    @Column("asignado_liquidacion")
    @JsonProperty("assignedLiquidation")
    val asignadoLiquidacion: Boolean? = null,
    @Column("id_tipo_nc")
    @JsonProperty("ncTypeId")
    val idTipoNotaCredito: Int? = null,
    @Column("valor_pagado")
    @JsonProperty("paidValue")
    val valorPagado: BigDecimal = BigDecimal.ZERO,
    @Column("doc_porcentaje_descuento")
    @JsonProperty("discountPercentage")
    val porcentajeDescuento: BigDecimal = BigDecimal.ZERO,
    @Column("valor_cruzado")
    @JsonProperty("crossedValue")
    val valorCruzado: BigDecimal = BigDecimal.ZERO,
    @Column("doc_id_caja")
    @JsonProperty("cashId")
    val idCaja: Int? = null,
    @Column("doc_tiene_credito")
    @JsonProperty("hasCredit")
    val tieneCredito: Boolean = false,
    @Column("doc_id_categoria_cliente")
    @JsonProperty("clientCategoryId")
    val idCategoriaCliente: Int? = null,
    @Column("es_caja_chica")
    @JsonProperty("isPettyCash")
    val esCajaChica: Boolean? = null,
    @Column("is_enviado_correo")
    @JsonProperty("isEmailSent")
    val isEnviadoCorreo: Boolean = false,
    @CreatedBy
    @Column("id_usuario_creacion")
    @JsonProperty("creationUserId")
    override val idUsuarioCreacion: Int? = null,
    @LastModifiedBy
    @Column("id_usuario_modificacion")
    @JsonProperty("modificationUserId")
    override val idUsuarioModificacion: Int? = null,
    @CreatedDate
    @Column("fecha_creacion")
    @JsonProperty("registrationDate")
    override val fechaCreacion: LocalDateTime? = null,
    @LastModifiedDate
    @Column("fecha_modificacion")
    @JsonProperty("modificationDate")
    override val fechaModificacion: LocalDateTime? = null,
) : BaseEntity(
    idUsuarioCreacion = idUsuarioCreacion,
    idUsuarioModificacion = idUsuarioModificacion,
    fechaCreacion = fechaCreacion,
    fechaModificacion = fechaModificacion,
)
