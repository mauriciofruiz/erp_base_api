package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("SUSTENTOTRIBUTARIO")
data class SustentoTributario(
    @Id
    @Column("ID_SUSTENTOTRIBUTARIO")
    @JsonProperty("taxSupportId")
    val idSustentoTributario: String,
    @Column("NOMBRE_SUSTENTOTRIBUTARIO")
    @JsonProperty("taxSupportName")
    val nombreSustentoTributario: String? = null,
)
