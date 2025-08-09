package com.sicon.tba.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleEstadoCuentaPagarDto {

    @JsonProperty("invoiceNumber")
    private String numeroFactura;

    @JsonProperty("issueDateInvoice")
    private LocalDate fechaEmisionFactura;

    @JsonProperty("dueDateInvoice")
    private LocalDate fechaVencimientoFactura;

    @JsonProperty("daysCredit")
    private Integer diasCredito;

    @JsonProperty("daysLate")
    private Integer diasMora;

    @JsonProperty("invoiceValue")
    private BigDecimal valorFactura;

    @JsonProperty("quotaValue")
    private BigDecimal valorCuota;

    @JsonProperty("balanceFee")
    private BigDecimal saldoCuota;

}
