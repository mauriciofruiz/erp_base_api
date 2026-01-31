package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CIUDAD")
data class Ciudad(
    @Id
    @Column("ID_CIUDAD")
    @JsonProperty("cityId")
    val idCiudad: Int? = null,

    @Column("ID_PROVINCIA")
    @JsonProperty("provinceId")
    val idProvincia: String? = null,

    @Column("NOMBRE_CIUDAD")
    @JsonProperty("cityName")
    val nombreCiudad: String? = null,

    @Column("CODIGO_CIUDAD")
    @JsonProperty("cityCode")
    val codigoCiudad: String? = null,
)
