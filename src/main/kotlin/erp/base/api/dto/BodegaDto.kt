package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BodegaDto(
    @JsonProperty("warehouseId")
    var bodegaId: Int? = null,
    @JsonProperty("branchId")
    var sucursalId: Int? = null,
    @JsonProperty("warehouseCode")
    var nemonicoBodega: String? = null,
    @JsonProperty("warehouseName")
    var nombreBodega: String? = null,
    @JsonProperty("warehouseSequence")
    var secuenciaBodega: Int? = null,
    @JsonProperty("warehouseTypeId")
    var tipoBodeg: Int? = null,
)
