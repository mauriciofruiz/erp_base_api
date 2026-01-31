package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class EmployeeInformationDto(
    @JsonProperty("employeeInformation")
    val empleadoDto: EmpleadoDto? = null,
    @JsonProperty("informationContractualEmployee")
    val informacionContractualDto: InformacionContractualDto? = null,
)
