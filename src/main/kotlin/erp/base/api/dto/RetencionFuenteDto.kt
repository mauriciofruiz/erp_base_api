package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class RetencionFuenteDto(
    @JsonProperty("accountId")
    val idCuenta: Int? = null,
    @field:NotNull(message = "El detalle de la retención en la fuente no puede estar vacío")
    @JsonProperty("retentionDetail")
    val detalleRetencionFuente: String? = null,
    @field:NotNull(message = "El porcentaje de la retención en la fuente no puede estar vacío")
    @JsonProperty("retentionPercentage")
    val porcentajeRetencionFuente: BigDecimal? = null,
    @field:NotNull(message = "El campo form 03 no puede estar vacío")
    @JsonProperty("form03Field")
    val campoForm03RetencionFuente: Int? = null,
    @field:NotNull(message = "El código del anexo de la retención en la fuente no puede estar vacío")
    @JsonProperty("attachedRetentionCode")
    val codigoAnexoRetencionFuente: String? = null,
    @JsonProperty("canceledRetention")
    val anuladoRetencionFuente: Boolean = false,
)
