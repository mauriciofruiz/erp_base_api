package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("UNIDADMEDIDA")
data class UnidadMedida(
    @Id
    @Column("ID_UNIDADMEDIDA")
    @JsonProperty("unitOfMeasureId")
    val idUnidadMedida: Int? = null,

    @Column("NOMBRE_UNIDADMEDIDA")
    @JsonProperty("unitOfMeasureName")
    val nombreUnidadMedida: String? = null,

    @Column("TIPO_UNIDADMEDIDA")
    @JsonProperty("unitOfMeasureType")
    val tipoUnidadMedida: String? = null,
)
