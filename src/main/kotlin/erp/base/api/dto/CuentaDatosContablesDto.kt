package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CuentaDatosContablesDto(
    @JsonProperty("countEmployeeId")
    val idEmpleadocuenta: Int? = null,
    @JsonProperty("employeeId")
    val idEmpleado: Int? = null,
    @JsonProperty("accountId")
    val idCuenta: Int? = null,
    @JsonProperty("accountTypeId")
    val idTipocuenta: Int? = null,
    @JsonProperty("accountNumber")
    val numeroCuenta: String? = null,
    @JsonProperty("parentAccountNumber")
    val numeroCuentapadre: String? = null,
    @JsonProperty("accountName")
    val nombreCuenta: String? = null,
)
