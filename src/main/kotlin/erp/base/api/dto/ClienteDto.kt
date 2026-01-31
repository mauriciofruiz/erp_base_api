package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class ClienteDto(
    @field:NotNull(message = "El id del cliente no puede ser nulo")
    @JsonProperty("clientCategoryId")
    var idCategoriaCliente: Int? = null,
    @field:NotNull(message = "El id del origen ingreso no puede ser nulo")
    @JsonProperty("incomeOriginId")
    var idOrigenIngreso: Int? = null,
    @field:NotBlank(message = "El id del tipo de identificacion fiscal no puede ser nulo")
    @JsonProperty("fiscalIdentificationTypeId")
    var idTipoIdFiscal: String? = null,
    @field:NotNull(message = "El id de la persona no puede ser nulo")
    @JsonProperty("personId")
    var idPersona: Int? = null,
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
    @JsonProperty("plate")
    var placa: String? = null,
)
