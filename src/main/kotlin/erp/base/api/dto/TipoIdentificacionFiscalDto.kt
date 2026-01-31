package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TipoIdentificacionFiscalDto(
    @JsonProperty("fiscalIdentificationTypeId")
    val idTipoIdFiscal: String,
    @JsonProperty("fiscalIdentificationTypeTransaction")
    val tipoTransaccionTipoIdFiscal: String? = null,
    @JsonProperty("fiscalIdentificationTypeName")
    val tipoIdentificacionTipoIdFiscal: String? = null,
)
