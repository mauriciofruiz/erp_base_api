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

@Table("TBA_RESPUESTAS_DOCUMENTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaRespuestasDocumento {
    @Id
    @Column("ID_RESPUESTAS_DOCUMENTO")
    @JsonProperty("responseDocumentId")
    private Integer idRespuestasDocumento;

    @Column("CODIGO_ERRROR_RESP")
    @JsonProperty("errorResponseCode")
    private String codigoErrorResp;

    @Column("CODIGO_RESPUESTA_RESP")
    @JsonProperty("responseCode")
    private String codigoRespuestaResp;

    @Column("FECHA_RESP")
    @JsonProperty("responseDate")
    private LocalDateTime fechaResp;

    @Column("XML_RESP")
    @JsonProperty("responseXml")
    private String xmlResp;

    @Column("ID_DOCUMENTO_ELECTRONICO")
    @JsonProperty("electronicDocumentId")
    private Integer idDocumentoElectronico;
}
