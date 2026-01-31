package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("DOCUMENTOVERIFICACION")
data class DocumentoVerificacion(
    @Id
    @Column("ID_DOCUMENTOVERIFICACION")
    @JsonProperty("verificationDocumentId")
    val idDocumentoVerificacion: Int,
    @Column("NUMERO_DOCUMENTOVERIFICACION")
    @JsonProperty("verificationDocumentNumber")
    val numeroDocumentoVerificacion: Int? = null,
    @Column("TIPO_DOCUMENTOVERIFICACION")
    @JsonProperty("verificationDocumentType")
    val tipoDocumentoVerificacion: String? = null,
)
