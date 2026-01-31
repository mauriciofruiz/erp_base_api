package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("GENERO")
data class Genero(
    @Id
    @Column("ID_GENERO")
    @JsonProperty("genderId")
    val idGenero: Int? = null,

    @Column("NOMBRE_GENERO")
    @JsonProperty("genderName")
    val nombreGenero: String? = null,

    @Column("COD_GENERO")
    @JsonProperty("genderCode")
    val codigoGenero: String? = null,
)
