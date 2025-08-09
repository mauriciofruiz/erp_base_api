package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PersonaProveedorDto(
        @JsonProperty("person")
        TbaPersonaDto persona,
        @JsonProperty("provider")
        TbaProveedorDto proveedor
) {
}
