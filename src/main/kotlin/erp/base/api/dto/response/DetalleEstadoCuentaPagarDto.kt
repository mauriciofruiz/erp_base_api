package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate

data class DetalleEstadoCuentaPagarDto(
    @JsonProperty("invoiceNumber")
    val numeroFactura: String? = null,
    @JsonProperty("issueDateInvoice")
    val fechaEmisionFactura: LocalDate? = null,
    @JsonProperty("dueDateInvoice")
    val fechaVencimientoFactura: LocalDate? = null,
    @JsonProperty("daysCredit")
    val diasCredito: Int? = null,
    @JsonProperty("daysLate")
    val diasMora: Int? = null,
    @JsonProperty("invoiceValue")
    val valorFactura: BigDecimal? = null,
    @JsonProperty("quotaValue")
    val valorCuota: BigDecimal? = null,
    @JsonProperty("balanceFee")
    val saldoCuota: BigDecimal? = null,
)
