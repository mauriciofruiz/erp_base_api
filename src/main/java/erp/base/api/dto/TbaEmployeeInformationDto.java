package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TbaEmployeeInformationDto {

    @JsonProperty("employeeInformation")
    private TbaEmpleadoDto empleadoDto;

    @JsonProperty("informationContractualEmployee")
    private TbaInformacionContractualDto informacionContractualDto;
}
