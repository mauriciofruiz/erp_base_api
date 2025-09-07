package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaInformacionContractualDto {

    @JsonProperty("companyEntryDate")
    private LocalDate fechaingresoempresaEmpleado;

    @JsonProperty("employeeReentryDate")
    private LocalDate fechareingresoEmpleado;

    @JsonProperty("branchId")
    private Integer idSucursal;

    @JsonProperty("branchName")
    private String nombreSucursal;

    @JsonProperty("employeeSalary")
    private BigDecimal salarioEmpleado;

    @JsonProperty("departmentId")
    private Integer idDepartamento;

    @JsonProperty("departmentName")
    private String nombreDepartamento;

    @JsonProperty("positionId")
    private Integer idCargo;

    @JsonProperty("jobName")
    private String nombreCargo;

    @JsonProperty("vacationProvision")
    private Boolean provisionvacacionesEmpleado;

    @JsonProperty("fortnight")
    private Boolean quincenaEmpleado;

    @JsonProperty("fortnightValue")
    private BigDecimal valorquincenaEmpleado;

    @JsonProperty("employerIESS")
    private Boolean iesspersonalEmpleado;

    @JsonProperty("employerPatronalIESS")
    private Boolean iesspatronalEmpleado;

    @JsonProperty("accumulatedThirteenth")
    private Boolean decimo3acumuladoEmpleado;

    @JsonProperty("accumulatedFourteenth")
    private Boolean decimo4acumuladoEmpleado;

    @JsonProperty("accumulatedReserveFundsEmployee")
    private Boolean fracumuladosEmpleado;

    @JsonProperty("incomeTax")
    private BigDecimal impuestorentaEmpleado;

    @JsonProperty("departureDate")
    private LocalDate fechasalidaEmpleado;

    @JsonProperty("departureReason")
    private String motivosalidaEmpleado;

    @JsonProperty("reentryReason")
    private String motivoreingresoEmpleado;

    @JsonProperty("listContract")
    private List<RrhhContratosDto> rrhhContratosDtos;

    @JsonProperty("countAccountingData")
    private List<ConCuentaDatosContablesDto> conCuentaDatosContablesDtos;


}
