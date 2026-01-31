package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("DOCUMENTOCASOSVERIFICACION")
data class DocumentoCasosVerificacion(
    @Id
    @Column("ID_DOCUMENTOCASOSVERIFICACION")
    @JsonProperty("documentVerificationCaseId")
    val idDocumentoCasosVerificacion: Int,
    @Column("ESTADO_DOCUMENTOCASOSVERIFICACION")
    @JsonProperty("documentVerificationCaseState")
    val estadoDocumentoCasosVerificacion: Int? = null,
    @Column("IDCASO_DOCUMENTOCASOSVERIFICACION")
    @JsonProperty("documentVerificationCaseIdCase")
    val idCasoDocumentoCasosVerificacion: Int? = null,
    @Column("IDDOCUMENTO_DOCUMENTOCASOSVERIFICACION")
    @JsonProperty("documentVerificationCaseIdDocumento")
    val idDocumentoDocumentoCasosVerificacion: Int? = null,
)
