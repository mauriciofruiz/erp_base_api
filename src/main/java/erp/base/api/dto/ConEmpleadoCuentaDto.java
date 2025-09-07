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
public class ConEmpleadoCuentaDto {

    @JsonProperty("employeeCountId")
    private Integer idEmpleadoCuenta;
    
    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @JsonProperty("countId")
    private Integer idCuenta;
}
