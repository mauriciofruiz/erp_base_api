package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PROVINCIA")
data class Provincia(
    @Id
    @Column("ID_PROVINCIA")
    @JsonProperty("provinceId")
    val idProvincia: String? = null,

    @Column("ID_PAIS")
    @JsonProperty("countryId")
    val idPais: Int? = null,

    @Column("NOMBRE_PROVINCIA")
    @JsonProperty("provinceName")
    val nombreProvincia: String? = null,

    @Column("COD_REGION")
    @JsonProperty("regionCode")
    val codRegion: Int? = null,
)
