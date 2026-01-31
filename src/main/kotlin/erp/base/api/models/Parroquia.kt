package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PARROQUIA")
data class Parroquia(
    @Id
    @Column("ID_PARROQUIA")
    @JsonProperty("parishId")
    val idParroquia: Int? = null,

    @Column("ID_CIUDAD")
    @JsonProperty("cityId")
    val idCiudad: Int? = null,

    @Column("NOMBRE_PARROQUIA")
    @JsonProperty("parishName")
    val nombreParroquia: String? = null,

    @Column("COD_PARROQUIA")
    @JsonProperty("parishCode")
    val codParroquia: String? = null,
)
