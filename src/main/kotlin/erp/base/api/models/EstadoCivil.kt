package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ESTADOCIVIL")
data class EstadoCivil(
    @Id
    @Column("ID_ESTADOCIVIL")
    @JsonProperty("maritalStatusId")
    val idEstadoCivil: Int? = null,

    @Column("NOMBRE_ESTADOCIVIL")
    @JsonProperty("maritalStatusName")
    val nombreEstadoCivil: String? = null,

    @Column("COD_ESTADOCIVIL")
    @JsonProperty("maritalStatusCode")
    val codigoEstadoCivil: String? = null,
)
