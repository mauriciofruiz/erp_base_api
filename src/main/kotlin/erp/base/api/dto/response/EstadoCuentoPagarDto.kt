package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class EstadoCuentoPagarDto(
    @JsonProperty("totalInvoiceValue")
    val totalValorFacturas: BigDecimal? = null,
    @JsonProperty("totalAdvances")
    val totalAnticipos: BigDecimal? = null,
    @JsonProperty("totalBalanceFee")
    val totalSaldoCuota: BigDecimal? = null,
    @JsonProperty("totalCurrent")
    val totalVigente: BigDecimal? = null,
    @JsonProperty("totalOverdue")
    val totalVencido: BigDecimal? = null,
    @JsonProperty("total1to30")
    val total1a30: BigDecimal? = null,
    @JsonProperty("total31to60")
    val total31a60: BigDecimal? = null,
    @JsonProperty("total61to90")
    val total61a90: BigDecimal? = null,
    @JsonProperty("total91more")
    val total91mas: BigDecimal? = null,
    @JsonProperty("detailAccountPayableStatus")
    val detalleEstadoCuentaPagarDtoList: List<DetalleEstadoCuentaPagarDto>? = null,
)
