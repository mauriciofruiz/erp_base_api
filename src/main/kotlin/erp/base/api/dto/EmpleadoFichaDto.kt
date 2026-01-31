package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate

data class EmpleadoFichaDto(
    @JsonProperty("employeeId")
    val idEmpleado: Int? = null,
    @JsonProperty("personIdentification")
    val identificacionPersona: String? = null,
    @JsonProperty("personName")
    val nombrePersona: String? = null,
    @JsonProperty("personCellphone")
    val celularPersona: String? = null,
    @JsonProperty("personEmail")
    val correoPersona: String? = null,
    @JsonProperty("imgUser")
    val imgUsr: String? = null,
    @JsonProperty("positionName")
    val nombreCargo: String? = null,
    @JsonProperty("nameBranch")
    val nombreSucursal: String? = null,
    @JsonProperty("employeeSalary")
    val salarioEmpleado: BigDecimal? = null,
    @JsonProperty("employeeEntryDate")
    val fechaingresoempresa_empleado: LocalDate? = null,
    @JsonProperty("employeeReentryDate")
    val fechareingresoEmpleado: LocalDate? = null,
    @JsonProperty("status")
    val estado: Boolean? = null,
)
