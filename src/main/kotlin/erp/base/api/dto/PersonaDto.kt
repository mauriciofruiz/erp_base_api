package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalDateTime

data class PersonaDto(
    @JsonProperty("creationUserId")
    var idUsuarioCreacion: Int? = null,
    @JsonProperty("modificationUserId")
    var idUsuarioModificacion: Int? = null,
    @JsonProperty("registrationDate")
    var fechaCreacion: LocalDateTime? = null,
    @JsonProperty("modificationDate")
    var fechaModificacion: LocalDateTime? = null,
    @JsonProperty("personId")
    var idPersona: Int? = null,
    @JsonProperty("contributorTypeId")
    var idTipoContribuyente: String? = null,
    @JsonProperty("countryId")
    var idPais: Int? = null,
    @JsonProperty("parishId")
    var idParroquia: Int? = null,
    @JsonProperty("maritalStatusId")
    var idEstadoCivil: Int? = null,
    @JsonProperty("genderId")
    var idGenero: Int? = null,
    @JsonProperty("taxIdentificationId")
    var idIdentificacionFiscal: Int? = null,
    @field:Pattern(regexp = "\\d{10,13}", message = "La identificación de la persona debe tener entre 10 y 13 dígitos")
    @JsonProperty("personIdentification")
    var identificacionPersona: String? = null,
    @JsonProperty("personName")
    var nombrePersona: String? = null,
    @JsonProperty("personBirthDate")
    var fechaNacimientoPersona: LocalDate? = null,
    @field:NotNull(message = "La dirección de la persona no puede estar vacía")
    @field:Size(min = 5, max = 500, message = "La dirección de la persona debe tener entre 5 y 500 caracteres")
    @JsonProperty("personAddress")
    var direccionPersona: String? = null,
    @field:NotNull(message = "El celular de la persona no puede estar vacío")
    @JsonProperty("personCellphone")
    var celularPersona: String? = null,
    @JsonProperty("personPhone")
    var telefonoPersona: String? = null,
    @JsonProperty("personEmail")
    var correoPersona: String? = null,
    @JsonProperty("isClient")
    var esClientePersona: Boolean? = null,
    @JsonProperty("isEmployee")
    var esEmpleadoPersona: Boolean? = null,
    @JsonProperty("isProvider")
    var esProveedorPersona: Boolean? = null,
    @JsonProperty("personRegistrationDate")
    var fechaRegistroPersona: LocalDateTime? = null,
    @JsonProperty("personResidenceNeighborhood")
    var barrioDomicilioPersona: String? = null,
    @JsonProperty("personResidenceCity")
    var cantonDomicilioPersona: String? = null,
    @JsonProperty("personResidenceParish")
    var parroquiaDomicilioPersona: String? = null,
    @JsonProperty("personResidenceSector")
    var sectorDomicilioPersona: String? = null,
)
