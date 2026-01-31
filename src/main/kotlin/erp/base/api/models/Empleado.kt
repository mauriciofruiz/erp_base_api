package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate

@Table("EMPLEADO")
data class Empleado(
    @Id
    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    var idEmpleado: Int? = null,
    @Column("ID_POSESIONDOMICILIO")
    @JsonProperty("domicilePossessionId")
    var idPosesionDomicilio: Int? = null,
    @Column("ID_CODIGOSECTORIAL")
    @JsonProperty("sectorialCodeId")
    var idCodigoSectorial: Int? = null,
    @Column("ID_TIPOSANGRE")
    @JsonProperty("bloodTypeId")
    var idTipoSangre: Int? = null,
    @Column("ID_TIPOLICENCIACONDUCIR")
    @JsonProperty("drivingLicenseTypeId")
    var idTipoLicenciaConducir: Int? = null,
    @Column("ID_CARGO")
    @JsonProperty("positionId")
    var idCargo: Int? = null,
    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    var idSucursal: Int? = null,
    @Column("ID_PERSONA")
    @JsonProperty("personId")
    var idPersona: Int? = null,
    @Column("ID_DISCAPACIDAD")
    @JsonProperty("disabilityId")
    var idDiscapacidad: Int? = null,
    @Column("NUMEROHIJOS_EMPLEADO")
    @JsonProperty("numberOfChildren")
    var numeroHijosEmpleado: Int? = null,
    @Column("CELULAR_EMPLEADO")
    @JsonProperty("employeeCellphone")
    var celularEmpleado: String? = null,
    @Column("TELEFONO_EMPLEADO")
    @JsonProperty("employeePhone")
    var telefonoEmpleado: String? = null,
    @Column("CORREO_EMPLEADO")
    @JsonProperty("employeeEmail")
    var correoEmpleado: String? = null,
    @Column("FECHACADUCALICENCIA_EMPLEADO")
    @JsonProperty("driverLicenseExpirationDate")
    var fechaCaducaLicenciaEmpleado: LocalDate? = null,
    @Column("FECHAINGRESOEMPRESA_EMPLEADO")
    @JsonProperty("employeeEntryDate")
    var fechaIngresoEmpresaEmpleado: LocalDate? = null,
    @Column("FECHAREINGRESO_EMPLEADO")
    @JsonProperty("employeeReentryDate")
    var fechaReingresoEmpleado: LocalDate? = null,
    @Column("SALARIO_EMPLEADO")
    @JsonProperty("employeeSalary")
    var salarioEmpleado: BigDecimal? = null,
    @Column("HORAORDINARIA_EMPLEADO")
    @JsonProperty("ordinaryHour")
    var horaOrdinariaEmpleado: BigDecimal? = null,
    @Column("ESDISCAPACITADO_EMPLEADO")
    @JsonProperty("employeeDisabled")
    var esDiscapacitadoEmpleado: Boolean? = null,
    @Column("PORCDISCAPACIDAD_EMPLEADO")
    @JsonProperty("disabilityPercentage")
    var porcentajeDiscapacidadEmpleado: BigDecimal? = null,
    @Column("DECIMO3ACUMULADO_EMPLEADO")
    @JsonProperty("accumulatedThirteenth")
    var decimoTerceroAcumuladoEmpleado: Boolean? = null,
    @Column("DECIMO4ACUMULADO_EMPLEADO")
    @JsonProperty("accumulatedFourteenth")
    var decimoCuartoAcumuladoEmpleado: Boolean? = null,
    @Column("FRACUMULADOS_EMPLEADO")
    @JsonProperty("accumulatedVacationFunds")
    var fraccionalesAcumuladosEmpleado: Boolean? = null,
    @Column("PROVISIONVACACIONES_EMPLEADO")
    @JsonProperty("vacationProvision")
    var provisionVacacionesEmpleado: Boolean? = null,
    @Column("QUINCENA_EMPLEADO")
    @JsonProperty("fortnight")
    var quincenaEmpleado: Boolean? = null,
    @Column("VALORQUINCENA_EMPLEADO")
    @JsonProperty("fortnightValue")
    var valorQuincenaEmpleado: BigDecimal? = null,
    @Column("CORREOEMPRESA_EMPLEADO")
    @JsonProperty("companyEmail")
    var correoEmpresaEmpleado: String? = null,
    @Column("USUARIOSKYPE_EMPLEADO")
    @JsonProperty("skypeUser")
    var usuarioSkypeEmpleado: String? = null,
    @Column("TELEFONOEMPRESA_EMPLEADO")
    @JsonProperty("companyPhone")
    var telefonoEmpresaEmpleado: String? = null,
    @Column("EXTENSION_EMPLEADO")
    @JsonProperty("extension")
    var extensionEmpleado: String? = null,
    @Column("CELULAREMPRESA_EMPLEADO")
    @JsonProperty("companyCellphone")
    var celularEmpresaEmpleado: String? = null,
    @Column("USUARIOSISTEMA_EMPLEADO")
    @JsonProperty("systemUser")
    var usuarioSistemaEmpleado: String? = null,
    @Column("FECHASALIDA_EMPLEADO")
    @JsonProperty("departureDate")
    var fechaSalidaEmpleado: LocalDate? = null,
    @Column("MOTIVOSALIDA_EMPLEADO")
    @JsonProperty("departureReason")
    var motivoSalidaEmpleado: String? = null,
    @Column("ESTADO_EMPLEADO")
    @JsonProperty("employeeStatus")
    var estadoEmpleado: Boolean? = null,
    @Column("TITULO_EMPLEADO")
    @JsonProperty("employeeTitle")
    var tituloEmpleado: String? = null,
    @Column("ID_NIVELESTUDIO")
    @JsonProperty("educationLevelId")
    var idNivelEstudio: Int? = null,
    @Column("IESSPATRONAL_EMPLEADO")
    @JsonProperty("employerIESS")
    var iessPatronalEmpleado: Boolean? = null,
    @Column("IESSPERSONAL_EMPLEADO")
    @JsonProperty("personalIESS")
    var iessPersonalEmpleado: Boolean? = null,
    @Column("MOTIVOREINGRESO_EMPLEADO")
    @JsonProperty("reentryReason")
    var motivoReingresoEmpleado: String? = null,
    @Column("IMPUESTORENTAANUAL_EMPLEADO")
    @JsonProperty("annualIncomeTax")
    var impuestoRentaAnualEmpleado: BigDecimal? = null,
    @Column("IMPUESTORENTA_EMPLEADO")
    @JsonProperty("incomeTax")
    var impuestoRentaEmpleado: BigDecimal? = null,
    @Column("IMPUESTORENTAREBAJA_EMPLEADO")
    @JsonProperty("incomeTaxRebate")
    var impuestoRentaRebajaEmpleado: BigDecimal? = null,
    @Column("PATHCEDULA_EMPLEADO")
    @JsonProperty("identificationDocCopy")
    var pathCedulaEmpleado: String? = null,
    @Column("PATHLICENCIA_EMPLEADO")
    @JsonProperty("driveLicenceEmployee")
    var pathLicenciaEmpleado: String? = null,
    @Column("PATHCV_EMPLEADO")
    @JsonProperty("cvEmployee")
    var pathCvEmpleado: String? = null,
)
