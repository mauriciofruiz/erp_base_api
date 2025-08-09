package com.sicon.tba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;

public record TbaFormaPagoCobroDto(
        @JsonProperty("paymentCollectionMethodId")
        String idFormaPagoCobro,
        @JsonProperty("paymentCollectionMethodName")
        String nombreFormaPagoCobro,

        @JsonProperty("paymentCollectionMethodAlias")
        String aliasFormaPagoCobro,

        @JsonProperty("paymentCollectionMethodDiscount")
        BigDecimal descuentoFormaPagoCobro,

        @JsonProperty("paymentCollectionMethodCode")
        String codigoFormaPagoCobro,

        @JsonProperty("paymentCollectionMethodStatus")
        Boolean estadoFormaPagoCobro
) {
}

