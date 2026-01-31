package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PAIS")
data class Pais(
    @Id
    @Column("ID_PAIS")
    @JsonProperty("countryId")
    val idPais: Int? = null,

    @Column("CODIGOPAIS")
    @JsonProperty("countryCode")
    val codigoPais: String? = null,

    @Column("NOMBRE_PAIS")
    @JsonProperty("countryName")
    val nombrePais: String? = null,
)
