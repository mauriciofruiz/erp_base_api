package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class EmpleadoCuentaDto(
    @JsonProperty("employeeCountId")
    var idEmpleadoCuenta: Int? = null,
    @JsonProperty("employeeId")
    var idEmpleado: Int? = null,
    @JsonProperty("countId")
    var idCuenta: Int? = null,
)
