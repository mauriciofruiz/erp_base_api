package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import erp.base.api.dto.ExcepcionesClienteDescDto
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class PersonaClienteCreditoDto(
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
    @JsonProperty("clientCategoryId")
    var idCategoriaCliente: Int? = null,
    @field:NotNull(message = "El id del origen ingreso no puede ser nulo")
    @JsonProperty("incomeOriginId")
    var idOrigenIngreso: Int? = null,
    @field:NotBlank(message = "El id del tipo de identificacion fiscal no puede ser nulo")
    @JsonProperty("fiscalIdentificationTypeId")
    var idTipoIdFiscal: String? = null,
    @field:NotNull(message = "El id de la cuenta no puede ser nulo")
    @JsonProperty("accountId")
    var idCuenta: Int? = null,
    @field:NotBlank(message = "El nombre comercial del cliente no puede estar vacio")
    @JsonProperty("clientCommercialName")
    var nombreComercialCliente: String? = null,
    @JsonProperty("clientAddress2")
    var direccion2Cliente: String? = null,
    @JsonProperty("clientEmail2")
    var correo2Cliente: String? = null,
    @JsonProperty("clientCellphone2")
    var celular2Cliente: String? = null,
    @JsonProperty("clientPhone2")
    var telefono2Cliente: String? = null,
    @JsonProperty("clientBalance")
    var saldoAFavorCliente: BigDecimal? = null,
    @JsonProperty("clientContact")
    var contactoCliente: String? = null,
    @JsonProperty("clientContactPosition")
    var cargoContactoCliente: String? = null,
    @JsonProperty("clientContactPhone")
    var telefonoContactoCliente: String? = null,
    @JsonProperty("clientContactEmail")
    var correoContactoCliente: String? = null,
    @JsonProperty("clientContactCellphone")
    var celularContactoCliente: String? = null,
    @JsonProperty("specialDiscount")
    var descuentoEspecialCliente: BigDecimal? = null,
    @JsonProperty("discountExpirationDate")
    var fechaCaducidadDescCliente: LocalDate? = null,
    @JsonProperty("discountObservation")
    var obsDescCliente: String? = null,
    @JsonProperty("generalAlert")
    var alertaGeneral: String? = null,
    @JsonProperty("billingAlert")
    var alertaFacturacion: String? = null,
    @JsonProperty("collectionAlert")
    var alertaCobro: String? = null,
    @JsonProperty("clientUnlockCode")
    var codDesbloqueoCliente: String? = null,
    @JsonProperty("clientChangeNumber")
    var numCambioCliente: Int? = null,
    @JsonProperty("clientInactiveReason")
    var motivoInactivaCliente: String? = null,
    @JsonProperty("clientStatus")
    var estadoCliente: Boolean? = null,
    @JsonProperty("salesUserId")
    var idUsuarioVentas: Int? = null,
    @JsonProperty("clientCashInvoice")
    var facContadoCliente: Boolean? = null,
    @JsonProperty("specialCategory")
    var categoriaEspecialCliente: String? = null,
    @JsonProperty("clientPolicy")
    var politicaCliente: String? = null,
    @JsonProperty("clientDiscount")
    var descuentoCliente: BigDecimal? = null,
    @JsonProperty("ivaRetentionId")
    var idRetencionIva: Int? = null,
    @JsonProperty("sourceRetentionId")
    var idRetencionFuente: Int? = null,
    @JsonProperty("creditClientId")
    var idCreditoCliente: Int? = null,
    @JsonProperty("clientId")
    var idCliente: Int? = null,
    @JsonProperty("authorizedCreditLimit")
    var cupoAutorizado: BigDecimal? = null,
    @JsonProperty("availableCreditLimit")
    var cupoDisponible: BigDecimal? = null,
    @JsonProperty("usedCreditLimit")
    var cupoUtilizado: BigDecimal? = null,
    @JsonProperty("guaranteeAmount")
    var montoGarantia: BigDecimal? = null,
    @JsonProperty("guaranteeDate")
    var fechaGarantia: LocalDateTime? = null,
    @JsonProperty("creditDays")
    var diasCredito: Int? = null,
    @JsonProperty("creditObservation")
    var observacion: String? = null,
    @JsonProperty("overlimit")
    var sobreCupo: BigDecimal? = null,
    @JsonProperty("overlimitExpirationDate")
    var fechaVencimientoSobreCupo: LocalDateTime? = null,
    @JsonProperty("registrationDate")
    var fechaCreacion: LocalDateTime? = null,
    @JsonProperty("modificationDate")
    var fechaModificacion: LocalDateTime? = null,
    @JsonProperty("cityId")
    var idCiudad: Int? = null,
    @JsonProperty("provinceId")
    var idProvincia: String? = null,
    @JsonProperty("plate")
    var placa: String? = null,
    @JsonProperty("exceptions")
    var excepciones: List<ExcepcionesClienteDescDto>? = null,
    @JsonProperty("statementAccount")
    var detalleEstadoCuentaCobrarDtoList: EstadoCuentoPagarDto? = null,
)
