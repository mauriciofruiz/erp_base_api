package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ORIGENINGRESO")
data class OrigenIngreso(
    @Id
    @Column("ID_ORIGENINGRESO")
    @JsonProperty("originId")
    val idOrigenIngreso: Int,
    @Column("NOMBRE_ORIGENINGRESO")
    @JsonProperty("originName")
    val nombreOrigenIngreso: String? = null,
    @Column("COD_ORIGENINGRESO")
    @JsonProperty("originCode")
    val codOrigenIngreso: String? = null,
)
