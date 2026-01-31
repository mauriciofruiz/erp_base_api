package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPODOC")
data class TipoDoc(
    @Id
    @Column("ID_TIPODOC")
    @JsonProperty("documentTypeId")
    val idTipoDoc: Int,

    @Column("COD_TIPODOC")
    @JsonProperty("documentTypeCode")
    val codigoTipoDoc: String? = null,

    @Column("NOMBRE_TIPODOC")
    @JsonProperty("documentTypeName")
    val nombreTipoDoc: String? = null,
)
