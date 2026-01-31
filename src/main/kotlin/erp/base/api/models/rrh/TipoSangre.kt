package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOSANGRE")
data class TipoSangre(
    @Id
    @Column("ID_TIPOSANGRE")
    @JsonProperty("bloodTypeId")
    val idTipoSangre: Int,
    @Column("NOMBRE_TIPOSANGRE")
    @JsonProperty("bloodTypeName")
    val nombreTipoSangre: String? = null,
)
