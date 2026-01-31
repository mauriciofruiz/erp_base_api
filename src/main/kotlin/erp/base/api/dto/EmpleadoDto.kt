package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate

data class EmpleadoDto(
    @JsonProperty("personId")
    var idPersona: Int? = null,
    @JsonProperty("employeeId")
    var idEmpleado: Int? = null,
    @JsonProperty("positionId")
    var idCargo: Int? = null,
    @JsonProperty("branchId")
    var idSucursal: Int? = null,
    @JsonProperty("personIdentification")
    var identificacionPersona: String? = null,
    @JsonProperty("personName")
    var nombrePersona: String? = null,
    @JsonProperty("personBirthDate")
    var fechanacimientoPersona: LocalDate? = null,
    @JsonProperty("bloodTypeId")
    var idTiposangre: Int? = null,
    @JsonProperty("bloodTypeName")
    var nombreTiposangre: String? = null,
    @JsonProperty("educationLevelId")
    var idNivelestudio: Int? = null,
    @JsonProperty("educationLevelName")
    var nombreNivelestudio: String? = null,
    @JsonProperty("cityName")
    var nombreCiudad: String? = null,
    @JsonProperty("parishId")
    var idParroquia: Int? = null,
    @JsonProperty("parishName")
    var nombreParroquia: String? = null,
    @JsonProperty("provinceName")
    var nombreProvincia: String? = null,
    @JsonProperty("maritalStatusId")
    var idEstadocivil: Int? = null,
    @JsonProperty("maritalStatusName")
    var nombreEstadocivil: String? = null,
    @JsonProperty("genderId")
    var idGenero: Int? = null,
    @JsonProperty("genderName")
    var nombreGenero: String? = null,
    @JsonProperty("employeeTitle")
    var tituloEmpleado: String? = null,
    @JsonProperty("personAddress")
    var direccionPersona: String? = null,
    @JsonProperty("personResidenceSector")
    var sectordomicilioPersona: String? = null,
    @JsonProperty("personEmail")
    var correoPersona: String? = null,
    @JsonProperty("personCellphone")
    var celularPersona: String? = null,
    @JsonProperty("employeePhone")
    var telefonoEmpleado: String? = null,
    @JsonProperty("employeeCellphone")
    var celularEmpleado: String? = null,
    @JsonProperty("drivingLicenseTypeId")
    var idTipolicenciaconducir: Int? = null,
    @JsonProperty("drivingLicenseTypeName")
    var nombreTipolicenciaconducir: String? = null,
    @JsonProperty("drivingLicenseTypeDescription")
    var descripcionTipolicenciaconducir: String? = null,
    @JsonProperty("driverLicenseExpirationDate")
    var fechacaducalicenciaEmpleado: LocalDate? = null,
    @JsonProperty("employeeDisabled")
    var esdiscapacitadoEmpleado: Boolean? = null,
    @JsonProperty("disabilityId")
    var idDiscapacidad: Int? = null,
    @JsonProperty("disabilityName")
    var nombreDiscapacidad: String? = null,
    @JsonProperty("disabilityPercentage")
    var porcdiscapacidadEmpleado: BigDecimal? = null,
    @JsonProperty("familyResponsibilities")
    var rrhCargasFamiliaresDtos: List<CargasFamiliaresDto>? = null,
    @JsonProperty("emergencyContact")
    var rrhContactoEmergenciaDtos: List<ContactoEmergenciaDto>? = null,
    @JsonProperty("bankAccount")
    var tbaCuentaBancariaDtos: List<CuentaBancariaDto>? = null,
    @JsonProperty("identificationDocCopy")
    var pathCedulaEmpleado: String? = null,
    @JsonProperty("driveLicenceEmployee")
    var pathLicenciaEmpleado: String? = null,
    @JsonProperty("cvEmployee")
    var pathCvEmpleado: String? = null,
    @JsonProperty("img")
    var imgUsr: String? = null,
)
