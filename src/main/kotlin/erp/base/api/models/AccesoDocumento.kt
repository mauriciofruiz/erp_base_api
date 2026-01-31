package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ACCESO_DOCUMENTO")
data class AccesoDocumento(
    @Id
    @Column("ID_ACCESO_DOCUMENTO")
    @JsonProperty("accessDocumentId")
    val idAccesoDocumento: Int,
    @NotNull
    @Column("ID_TIPO_DOCUMENTO")
    @JsonProperty("documentTypeId")
    val idTipoDocumento: Int,
    @NotNull
    @Column("ID_DOCUMENTO")
    @JsonProperty("documentId")
    val idDocumento: Int,
    @NotNull
    @Column("NAME_USUARIO")
    @JsonProperty("userName")
    val nameUsuario: String,
)
