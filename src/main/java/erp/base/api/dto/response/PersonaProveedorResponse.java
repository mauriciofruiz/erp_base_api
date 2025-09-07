package erp.base.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonaProveedorResponse {

    @NotNull
    @JsonProperty("personId")
    private Integer personaId;

    @NotNull
    @JsonProperty("providerId")
    private Integer proveedorId;

    @JsonProperty("personIdentification")
    private String identificacion;

    @JsonProperty("personName")
    private String nombre;

    @JsonProperty("providerName")
    private String nombreProveedor;

    @JsonProperty("personEmail")
    private String email;

    @JsonProperty("personAddress")
    private String direccion;

    @JsonProperty("personPhone")
    private String telefono;

    @JsonProperty("retentionSourceId")
    private Integer idRetencionFuente;

    @JsonProperty("retentionIVAId")
    private Integer idRetencionIva;

    @JsonProperty("creditDays")
    private Integer diasCredito;

    @JsonProperty("paymentConditionId")
    private Integer idCondicionPago;

    @JsonProperty("expenseAccountId")
    private Integer cuentaGastoProveedor;

    @JsonProperty("isForeign")
    private Boolean esExtranjero;

}
