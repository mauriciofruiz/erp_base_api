package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record TrbRetencionIvaDto(
        @JsonProperty("accountId")
        Integer idCuenta,

        @JsonProperty("retentionIvaCode")
        String codigoRetencionIva,

        @JsonProperty("retentionIvaDescription")
        String descripcionRetencionIva,

        @JsonProperty("retentionIvaPercentage")
        BigDecimal porcentajeRetencionIva,

        @JsonProperty("canceledRetentionIva")
        Boolean anuladoRetencionIva
        ) {
}
