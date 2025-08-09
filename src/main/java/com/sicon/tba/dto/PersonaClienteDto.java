package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicon.tba.models.TbaCreditoCliente;

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
