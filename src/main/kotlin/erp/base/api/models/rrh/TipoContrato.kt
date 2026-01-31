package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOCONTRATO")
data class TipoContrato(
    @Id
    @Column("ID_TIPOCONTRATO")
    @JsonProperty("contractTypeId")
    val idTipoContrato: Int,
    @Column("NOMBRE_TIPOCONTRATO")
    @JsonProperty("contractTypeName")
    val nombreTipoContrato: String? = null,
)
