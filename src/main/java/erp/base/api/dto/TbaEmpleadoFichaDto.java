package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbaEmpleadoFichaDto {

    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @JsonProperty("personIdentification")
    private String identificacionPersona;

    @JsonProperty("personName")
    private String nombrePersona;

    @JsonProperty("personCellphone")
    private String celularPersona;

    @JsonProperty("personEmail")
    private String correoPersona;

    @JsonProperty("imgUser")
    private String imgUsr;

    @JsonProperty("positionName")
    private String nombreCargo;

    @JsonProperty("nameBranch")
    private String nombreSucursal;

    @JsonProperty("employeeSalary")
    private BigDecimal salarioEmpleado;

    @JsonProperty("employeeEntryDate")
    private LocalDate fechaingresoempresa_empleado;

    @JsonProperty("employeeReentryDate")
    private LocalDate FECHAREINGRESO_EMPLEADO;

    @JsonProperty("status")
    private Boolean estado;


}
