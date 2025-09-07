package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BodegaDto {
    @JsonProperty("warehouseId")
    Integer bodegaId;

    @JsonProperty("branchId")
    Integer sucursalId;

    @JsonProperty("warehouseCode")
    String nemonicoBodega;

    @JsonProperty("warehouseName")
    String nombreBodega;

    @JsonProperty("warehouseSequence")
    Integer secuenciaBodega;

    @JsonProperty("warehouseTypeId")
    Integer tipoBodeg;
}
