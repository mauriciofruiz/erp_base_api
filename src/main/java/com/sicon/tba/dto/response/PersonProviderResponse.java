package com.sicon.tba.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicon.tba.models.Proveedor;
import com.sicon.tba.models.TbaPersona;
import lombok.Builder;

@Builder
public record PersonProviderResponse(
        @JsonProperty("person")
        TbaPersona persona,
        @JsonProperty("provider")
        Proveedor proveedor
) {
}
