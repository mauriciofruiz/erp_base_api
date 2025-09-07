package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

public record TrbRetencionFuenteDto(
        @JsonProperty("accountId") Integer idCuenta,
        @JsonProperty("retentionDetail") @NotNull(message = "El detalle de la retención en la fuente no puede estar vacío") String detalleRetencionFuente,
        @JsonProperty("retentionPercentage") @NotNull(message = "El porcentaje de la retención en la fuente no puede estar vacío") BigDecimal porcentajeRetencionFuente,
        @JsonProperty("form03Field") @NotNull(message="El campo form 03 no puede estar vacío") Integer campoForm03RetencionFuente,
        @JsonProperty("attachedRetentionCode") @NotNull(message="El código del anexo de la retención en la fuente no puede estar vacío") String codigoAnexoRetencionFuente,
        @JsonProperty("canceledRetention") boolean anuladoRetencionFuente
){
}
