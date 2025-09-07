package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmpleadoPersonaDto {

    @JsonProperty("personId")
    private Integer idPersona;

    @JsonProperty("employeeId")
    private Integer idEmpleado;


    @JsonProperty("branchId")
    private Integer idSucursal;

    @JsonProperty("branchName")
    private String nombreSucursal;

    @JsonProperty("personIdentification")
    private String identificacionPersona;

    @JsonProperty("personName")
    private String nombrePersona;

    @JsonProperty("fortnight")
    private Boolean quincenaEmpleado;

    @JsonProperty("fortnightValue")
    private BigDecimal valorquincenaEmpleado;

    @JsonProperty("countStateBank")
    private Boolean estadoCuentabancaria;

    @JsonProperty("methodPay")
    private String metodoPago;

    @JsonProperty("employeeStatus")
    private Boolean estadoEmpleado;
}
