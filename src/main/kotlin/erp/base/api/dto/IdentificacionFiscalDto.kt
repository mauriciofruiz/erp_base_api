package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class IdentificacionFiscalDto(
    @field:NotNull(message = "El código de identificación fiscal no puede estar vacío.")
    @JsonProperty("taxIdentificationCode")
    val codIdentificacionFiscal: String? = null,
    @field:NotNull(message = "El nombre de identificación fiscal no puede estar vacío.")
    @JsonProperty("taxIdentificationName")
    val nombreIdentificacionFiscal: String? = null,
    @field:NotNull(message = "El código de identificación fiscal de ATS no puede estar vacío.")
    @JsonProperty("atsIdentificationCode")
    val codAtsIdentificacionFiscal: String? = null,
)
