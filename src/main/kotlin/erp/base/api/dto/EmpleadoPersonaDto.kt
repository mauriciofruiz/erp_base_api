package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class EmpleadoPersonaDto(
    @JsonProperty("personId")
    var idPersona: Int? = null,
    @JsonProperty("employeeId")
    var idEmpleado: Int? = null,
    @JsonProperty("branchId")
    var idSucursal: Int? = null,
    @JsonProperty("branchName")
    var nombreSucursal: String? = null,
    @JsonProperty("personIdentification")
    var identificacionPersona: String? = null,
    @JsonProperty("personName")
    var nombrePersona: String? = null,
    @JsonProperty("fortnight")
    var quincenaEmpleado: Boolean? = null,
    @JsonProperty("fortnightValue")
    var valorquincenaEmpleado: BigDecimal? = null,
    @JsonProperty("countStateBank")
    var estadoCuentabancaria: Boolean? = null,
    @JsonProperty("methodPay")
    var metodoPago: String? = null,
    @JsonProperty("employeeStatus")
    var estadoEmpleado: Boolean? = null,
)
