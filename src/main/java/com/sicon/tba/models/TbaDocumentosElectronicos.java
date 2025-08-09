package com.sicon.tba.models;

        import com.fasterxml.jackson.annotation.JsonProperty;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.springframework.data.annotation.Id;
        import org.springframework.data.relational.core.mapping.Column;
        import org.springframework.data.relational.core.mapping.Table;

        import java.time.LocalDateTime;

@Table("TBA_DOCUMENTOS_ELECTRONICOS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaDocumentosElectronicos {
    @Id
    @Column("ID_DOCUMENTO_ELECTRONICO")
    @JsonProperty("electronicDocumentId")
    private Integer idDocumentoElectronico;

    @Column("ANULADO_DOC")
    @JsonProperty("cancelled")
    private boolean anuladoDoc;

    @Column("CLAVEACCESO_DOC")
    @JsonProperty("accessKey")
    private String claveAccesoDoc;

    @Column("ENVIO_CORREO_DOC")
    @JsonProperty("emailNotification")
    private boolean envioCorreoDoc;

    @Column("FECHA_AUTORIZACION_DOC")
    @JsonProperty("authorizationDate")
    private LocalDateTime fechaAutorizacionDoc;

    @Column("FECHA_EMISION_DOC")
    @JsonProperty("emissionDate")
    private LocalDateTime fechaEmisionDoc;

    @Column("ID_DOCUMENTO_DOC")
    @JsonProperty("documentId")
    private Integer idDocumentoDoc;

    @Column("IDENTIFICACION_DOC")
    @JsonProperty("identification")
    private String identificacionDoc;

    @Column("NOMBRE_DOC")
    @JsonProperty("name")
    private String nombreDoc;

    @Column("NROINTENTOS_DIA_DOC")
    @JsonProperty("dailyRetryAttempts")
    private Integer nroIntentosDiaDoc;

    @Column("NROINTENTOS_DOC")
    @JsonProperty("retryAttempts")
    private Integer nroIntentosDoc;

    @Column("NUM_DOC")
    @JsonProperty("documentNumber")
    private String numDoc;

    @Column("TIPO_DOC")
    @JsonProperty("documentType")
    private String tipoDoc;

    @Column("XML_AUTORIZADA_DOC")
    @JsonProperty("authorizedXml")
    private String xmlAutorizadaDoc;

    @Column("XML_FIRMADO_DOC")
    @JsonProperty("signedXml")
    private String xmlFirmadoDoc;

    @Column("XML_GENERADO_DOC")
    @JsonProperty("generatedXml")
    private String xmlGeneradoDoc;

    @Column("ID_ESTADOFACTURA")
    @JsonProperty("invoiceStateId")
    private Integer idEstadoFactura;

    @Column("ID_ESTADOSRI")
    @JsonProperty("sriStateId")
    private Integer idEstadoSri;
}
