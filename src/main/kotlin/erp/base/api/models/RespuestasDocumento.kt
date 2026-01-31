package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("RESPUESTAS_DOCUMENTO")
data class RespuestasDocumento(
    @Id
    @Column("ID_RESPUESTAS_DOCUMENTO")
    @JsonProperty("responseDocumentId")
    val idRespuestasDocumento: Int,
    @Column("CODIGO_ERRROR_RESP")
    @JsonProperty("errorResponseCode")
    val codigoErrorResp: String? = null,
    @Column("CODIGO_RESPUESTA_RESP")
    @JsonProperty("responseCode")
    val codigoRespuestaResp: String? = null,
    @Column("FECHA_RESP")
    @JsonProperty("responseDate")
    val fechaResp: LocalDateTime? = null,
    @Column("XML_RESP")
    @JsonProperty("responseXml")
    val xmlResp: String? = null,
    @Column("ID_DOCUMENTO_ELECTRONICO")
    @JsonProperty("electronicDocumentId")
    val idDocumentoElectronico: Int? = null,
)
