package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record TarifaIvaDto(
        @JsonProperty("taxRatePercentage") BigDecimal porcentajeTarifaIva,
        @JsonProperty("taxRateCode") String codigoTarifaIva,
        @JsonProperty("taxDescription")  String descripcionIva,
        @JsonProperty("saleAccountId") Integer idCuentaVenta,
        @JsonProperty("purchaseAccountId") Integer idCuentaCompra,
        @JsonProperty("taxRateState") boolean estadoTarifaIva
){
}
