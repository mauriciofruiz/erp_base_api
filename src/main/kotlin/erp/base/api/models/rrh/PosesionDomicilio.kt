package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("POSESIONDOMICILIO")
data class PosesionDomicilio(
    @Id
    @Column("ID_POSESIONDOMICILIO")
    @JsonProperty("homePossessionId")
    val idPosesionDomicilio: Int,
    @Column("NOMBRE_POSESIONDOMICILIO")
    @JsonProperty("homePossessionName")
    val nombrePosesionDomicilio: String? = null,
)
