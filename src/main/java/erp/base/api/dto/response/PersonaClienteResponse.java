package erp.base.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonaClienteResponse {
    @NotNull
    @JsonProperty("personId")
    private Integer personaId;

    @NotNull
    @JsonProperty("clientId")
    private Integer clienteId;

    @JsonProperty("personIdentification")
    private String identificacion;

    @JsonProperty("personName")
    private String nombre;

    @JsonProperty("clientName")
    private String nombreCliente;

    @JsonProperty("personEmail")
    private String email;

    @JsonProperty("authorizedQuota")
    private BigDecimal cupoAutorizado;

    @JsonProperty("usedQuota")
    private BigDecimal cupoUtilizado;

    @JsonProperty("overdraft")
    private BigDecimal sobreCupo;

    @JsonProperty("availableQuota")
    private BigDecimal cupoDisponible;

    @JsonProperty("clientCategory")
    private String categoriaCliente;

    @JsonProperty("clientContact")
    private String contactoCliente;

    @JsonProperty("personPhone")
    private String telefono;

    @JsonProperty("creditDays")
    private Integer diasCredito;

    @JsonProperty("creditObservation")
    private String observacion;

    @JsonProperty("plate")
    private String placa;

    @JsonProperty("taxIdentificationCode")
    private String codigoIdentificacionTributaria;
}
