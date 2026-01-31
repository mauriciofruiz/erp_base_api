package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ERRORES")
data class Errores(
    @Id
    @Column("ID_ERRORES")
    @JsonProperty("errorId")
    val idErrores: Int,
    @Column("DESCRIPCIONERROR_ERRORES")
    @JsonProperty("errorDescription")
    val descripcionErrorErrores: String? = null,
    @Column("DESCRIPCION_ERRORES")
    @JsonProperty("description")
    val descripcionErrores: String? = null,
    @Column("ERROR_ERRORES")
    @JsonProperty("error")
    val errorErrores: String? = null,
    @Column("IDDOCUMENTO_ERRORES")
    @JsonProperty("documentId")
    val idDocumentoErrores: Int? = null,
    @Column("MODULO_ERRORES")
    @JsonProperty("module")
    val moduloErrores: String? = null,
    @Column("REVISADO_ERRORES")
    @JsonProperty("reviewed")
    val revisadoErrores: Boolean = false,
    @Column("TIPODOCUMENTO_ERRORES")
    @JsonProperty("documentType")
    val tipoDocumentoErrores: String? = null,
)
