package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOCONTRIBUYENTE")
data class TipoContribuyente(
    @Id
    @Column("ID_TIPOCONTRIBUYENTE")
    @JsonProperty("contributorTypeId")
    val idTipoContribuyente: String? = null,

    @Column("NOMBRE_TIPOCONTRIBUYENTE")
    @JsonProperty("contributorTypeName")
    val nombreTipoContribuyente: String? = null,

    @Column("COD_TIPOCONTRIBUYENTE")
    @JsonProperty("contributorTypeCode")
    val codigoTipoContribuyente: String? = null,
)
