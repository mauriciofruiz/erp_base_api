package erp.base.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import erp.base.api.models.Proveedor;
import erp.base.api.models.TbaPersona;
import lombok.Builder;

@Builder
public record PersonProviderResponse(
        @JsonProperty("person")
        TbaPersona persona,
        @JsonProperty("provider")
        Proveedor proveedor
) {
}
