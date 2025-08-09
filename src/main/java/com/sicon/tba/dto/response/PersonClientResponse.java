package com.sicon.tba.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicon.tba.dto.ExcepcionesClienteDescDto;
import com.sicon.tba.models.TbaCliente;
import com.sicon.tba.models.TbaCreditoCliente;
import com.sicon.tba.models.TbaPersona;
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
