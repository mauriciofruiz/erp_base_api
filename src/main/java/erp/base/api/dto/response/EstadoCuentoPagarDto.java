package erp.base.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoCuentoPagarDto {

    @JsonProperty("totalInvoiceValue")
    private BigDecimal totalValorFacturas;

    @JsonProperty("totalAdvances")
    private BigDecimal totalAnticipos;

    @JsonProperty("totalBalanceFee")
    private BigDecimal totalSaldoCuota;

    @JsonProperty("totalCurrent")
    private BigDecimal totalVigente;

    @JsonProperty("totalOverdue")
    private BigDecimal totalVencido;

    @JsonProperty("total1to30")
    private BigDecimal total1a30;

    @JsonProperty("total31to60")
    private BigDecimal total31a60;

    @JsonProperty("total61to90")
    private BigDecimal total61a90 ;

    @JsonProperty("total91more")
    private BigDecimal total91mas;

    @JsonProperty("detailAccountPayableStatus")
    private List<DetalleEstadoCuentaPagarDto> detalleEstadoCuentaPagarDtoList;

}
