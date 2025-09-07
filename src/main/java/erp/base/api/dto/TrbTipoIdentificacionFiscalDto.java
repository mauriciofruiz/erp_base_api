package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record TrbTipoIdentificacionFiscalDto(
        @JsonProperty("fiscalIdentificationTypeId")
        String idTipoIdFiscal,

        @JsonProperty("fiscalIdentificationTypeTransaction")
        String tipoTransaccionTipoIdFiscal,

        @JsonProperty("fiscalIdentificationTypeName")
        String tipoIdentificacionTipoIdFiscal
) {
}
