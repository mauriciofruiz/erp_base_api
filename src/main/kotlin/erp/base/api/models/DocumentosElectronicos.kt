package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("DOCUMENTOS_ELECTRONICOS")
data class DocumentosElectronicos(
    @Id
    @Column("ID_DOCUMENTO_ELECTRONICO")
    @JsonProperty("electronicDocumentId")
    val idDocumentoElectronico: Int,
    @Column("ANULADO_DOC")
    @JsonProperty("cancelled")
    val anuladoDoc: Boolean = false,
    @Column("CLAVEACCESO_DOC")
    @JsonProperty("accessKey")
    val claveAccesoDoc: String? = null,
    @Column("ENVIO_CORREO_DOC")
    @JsonProperty("emailNotification")
    val envioCorreoDoc: Boolean = false,
    @Column("FECHA_AUTORIZACION_DOC")
    @JsonProperty("authorizationDate")
    val fechaAutorizacionDoc: LocalDateTime? = null,
    @Column("FECHA_EMISION_DOC")
    @JsonProperty("emissionDate")
    val fechaEmisionDoc: LocalDateTime? = null,
    @Column("ID_DOCUMENTO_DOC")
    @JsonProperty("documentId")
    val idDocumentoDoc: Int? = null,
    @Column("IDENTIFICACION_DOC")
    @JsonProperty("identification")
    val identificacionDoc: String? = null,
    @Column("NOMBRE_DOC")
    @JsonProperty("name")
    val nombreDoc: String? = null,
    @Column("NROINTENTOS_DIA_DOC")
    @JsonProperty("dailyRetryAttempts")
    val nroIntentosDiaDoc: Int? = null,
    @Column("NROINTENTOS_DOC")
    @JsonProperty("retryAttempts")
    val nroIntentosDoc: Int? = null,
    @Column("NUM_DOC")
    @JsonProperty("documentNumber")
    val numDoc: String? = null,
    @Column("TIPO_DOC")
    @JsonProperty("documentType")
    val tipoDoc: String? = null,
    @Column("XML_AUTORIZADA_DOC")
    @JsonProperty("authorizedXml")
    val xmlAutorizadaDoc: String? = null,
    @Column("XML_FIRMADO_DOC")
    @JsonProperty("signedXml")
    val xmlFirmadoDoc: String? = null,
    @Column("XML_GENERADO_DOC")
    @JsonProperty("generatedXml")
    val xmlGeneradoDoc: String? = null,
    @Column("ID_ESTADOFACTURA")
    @JsonProperty("invoiceStateId")
    val idEstadoFactura: Int? = null,
    @Column("ID_ESTADOSRI")
    @JsonProperty("sriStateId")
    val idEstadoSri: Int? = null,
)
