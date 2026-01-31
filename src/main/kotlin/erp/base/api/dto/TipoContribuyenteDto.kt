package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class TipoContribuyenteDto(
    @field:NotNull(message = "El id del tipo de contribuyente no puede estar vacío")
    @JsonProperty("contributorTypeId")
    val idTipoContribuyente: String,
    @field:NotNull(message = "El nombre del tipo de contribuyente no puede estar vacío")
    @JsonProperty("contributorTypeName")
    val nombreTipoContribuyente: String? = null,
    @field:NotNull(message = "El código del tipo de contribuyente no puede estar vacío")
    @JsonProperty("contributorTypeCode")
    val codigoTipoContribuyente: String? = null,
)
