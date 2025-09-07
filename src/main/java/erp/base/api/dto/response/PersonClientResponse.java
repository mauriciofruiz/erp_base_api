package erp.base.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import erp.base.api.dto.ExcepcionesClienteDescDto;
import erp.base.api.models.TbaCliente;
import erp.base.api.models.TbaCreditoCliente;
import erp.base.api.models.TbaPersona;
import lombok.Builder;

import java.util.List;

@Builder
public record PersonClientResponse(
        @JsonProperty("person")
        TbaPersona persona,
        @JsonProperty("client")
        TbaCliente cliente,

        @JsonProperty("credit")
        TbaCreditoCliente creditoCliente,

        @JsonProperty("exceptions")
        List<ExcepcionesClienteDescDto> excepciones
) {
}
