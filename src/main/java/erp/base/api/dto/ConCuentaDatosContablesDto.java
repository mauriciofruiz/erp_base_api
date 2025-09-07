package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConCuentaDatosContablesDto {

    @JsonProperty("countEmployeeId")
    private Integer idEmpleadocuenta;

    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @JsonProperty("accountId")
    private Integer idCuenta;

    @JsonProperty("accountTypeId")
    private Integer idTipocuenta;

    @JsonProperty("accountNumber")
    private String numeroCuenta;

    @JsonProperty("parentAccountNumber")
    private String numeroCuentapadre;

    @JsonProperty("accountName")
    private String nombreCuenta;


}
