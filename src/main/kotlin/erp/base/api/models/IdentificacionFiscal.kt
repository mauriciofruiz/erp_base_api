package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("IDENTIFICACIONFISCAL")
data class IdentificacionFiscal(
    @Id
    @Column("ID_IDENTIFICACIONFISCAL")
    @JsonProperty("taxIdentificationId")
    val idIdentificacionFiscal: Int? = null,

    @Column("COD_IDENTIFICACIONFISCAL")
    @JsonProperty("taxIdentificationCode")
    val codIdentificacionFiscal: String? = null,

    @Column("NOMBRE_IDENTIFICACIONFISCAL")
    @JsonProperty("taxIdentificationName")
    val nombreIdentificacionFiscal: String? = null,

    @Column("CODATS_IDENTIFICACIONFISCAL")
    @JsonProperty("atsIdentificationCode")
    val codAtsIdentificacionFiscal: String? = null,
)
