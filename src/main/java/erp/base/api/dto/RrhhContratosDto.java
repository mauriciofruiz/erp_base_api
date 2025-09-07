package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhhContratosDto {

    @JsonProperty("contractId")
    private Integer idContrato;

    @JsonProperty("contractTypeId")
    private Integer idTipocontrato;

    @JsonProperty("contractName")
    public String nombreTipocontrato;

    @JsonProperty("contractModalityId")
    private Integer idModalidadcontrato;

    @JsonProperty("nameContractType")
    public String nombreModalidadcontrato;

    @JsonProperty("startDateContract")
    public LocalDate fechainicioContrato;

    @JsonProperty("endDateContract")
    public LocalDate fechafinContrato;

    @JsonProperty("stateContract")
    public Boolean estadoContrato;

    @JsonProperty("fileContract")
    public String archivoContrato;

    @JsonProperty("contractHours")
    public BigDecimal nrohorasContrato;
}
