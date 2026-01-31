package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("MULTIDIMENSION")
data class Multidimension(
    @Id
    @Column("ID_MULTIDIMENSION")
    @JsonProperty("multidimensionId")
    val idMultidimension: Int? = null,

    @Column("TIPO_MULTIDIMENSION")
    @JsonProperty("multidimensionType")
    val tipoMultidimension: String? = null,

    @Column("VALOR_MULTIDIMENSION")
    @JsonProperty("multidimensionValue")
    val valorMultidimension: Int? = null,
)
