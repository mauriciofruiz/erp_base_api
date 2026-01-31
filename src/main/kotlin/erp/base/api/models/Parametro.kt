package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PARAMETRO")
data class Parametro(
    @Id
    @Column("ID_PARAMETRO")
    @JsonProperty("parameterId")
    val idParametro: Int,

    @Column("NOMBRE_PARAMETRO")
    @JsonProperty("parameterName")
    val nombreParametro: String? = null,

    @Column("VALOR_PARAMETRO")
    @JsonProperty("parameterValue")
    val valorParametro: String? = null,
)
