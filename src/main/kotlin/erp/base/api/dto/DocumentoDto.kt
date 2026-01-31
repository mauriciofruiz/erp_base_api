package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class DocumentoDto(
    @JsonProperty("documentId")
    var idDocumento: Int? = null,
    @JsonProperty("numberDocument")
    var numeroDocumento: String? = null,
    @JsonProperty("documentTypeId")
    var tipoComprobante: String? = null,
    @JsonProperty("documentTypeName")
    var nombreTipoDoc: String? = null,
    @JsonProperty("documentStateId")
    var estadoDocumento: Int? = null,
    @JsonProperty("documentComercialName")
    var nombreComercial: String? = null,
    @JsonProperty("documentIdentification")
    var identificacionDocumento: String? = null,
    @JsonProperty("issueDate")
    var fechaEmision: LocalDateTime? = null,
    @JsonProperty("accessKey")
    var claveAcceso: String? = null,
)
