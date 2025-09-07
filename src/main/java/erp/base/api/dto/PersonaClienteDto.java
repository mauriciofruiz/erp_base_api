package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import erp.base.api.models.TbaCreditoCliente;

import java.util.List;

public record PersonaClienteDto(
        @JsonProperty("person")
        TbaPersonaDto persona,
        @JsonProperty("client")
        TbaClienteDto cliente,

        @JsonProperty("credit")
        TbaCreditoCliente credito,

        @JsonProperty("exceptions")
        List<ExcepcionesClienteDescDto> excepciones
) {
}
