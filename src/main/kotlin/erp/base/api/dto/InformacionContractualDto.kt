package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate

data class InformacionContractualDto(
    @JsonProperty("companyEntryDate")
    var fechaingresoempresaEmpleado: LocalDate? = null,
    @JsonProperty("employeeReentryDate")
    var fechareingresoEmpleado: LocalDate? = null,
    @JsonProperty("branchId")
    var idSucursal: Int? = null,
    @JsonProperty("branchName")
    var nombreSucursal: String? = null,
    @JsonProperty("employeeSalary")
    var salarioEmpleado: BigDecimal? = null,
    @JsonProperty("departmentId")
    var idDepartamento: Int? = null,
    @JsonProperty("departmentName")
    var nombreDepartamento: String? = null,
    @JsonProperty("positionId")
    var idCargo: Int? = null,
    @JsonProperty("jobName")
    var nombreCargo: String? = null,
    @JsonProperty("vacationProvision")
    var provisionvacacionesEmpleado: Boolean? = null,
    @JsonProperty("fortnight")
    var quincenaEmpleado: Boolean? = null,
    @JsonProperty("fortnightValue")
    var valorquincenaEmpleado: BigDecimal? = null,
    @JsonProperty("employerIESS")
    var iesspersonalEmpleado: Boolean? = null,
    @JsonProperty("employerPatronalIESS")
    var iesspatronalEmpleado: Boolean? = null,
    @JsonProperty("accumulatedThirteenth")
    var decimo3acumuladoEmpleado: Boolean? = null,
    @JsonProperty("accumulatedFourteenth")
    var decimo4acumuladoEmpleado: Boolean? = null,
    @JsonProperty("accumulatedReserveFundsEmployee")
    var fracumuladosEmpleado: Boolean? = null,
    @JsonProperty("incomeTax")
    var impuestorentaEmpleado: BigDecimal? = null,
    @JsonProperty("departureDate")
    var fechasalidaEmpleado: LocalDate? = null,
    @JsonProperty("departureReason")
    var motivosalidaEmpleado: String? = null,
    @JsonProperty("reentryReason")
    var motivoreingresoEmpleado: String? = null,
    @JsonProperty("listContract")
    var rrhhContratosDtos: List<RrhhContratosDto>? = null,
    @JsonProperty("countAccountingData")
    var conCuentaDatosContablesDtos: List<CuentaDatosContablesDto>? = null,
)
