package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class PersonaProveedorResponse(
    @field:NotNull
    @JsonProperty("personId")
    var personaId: Int? = null,
    @field:NotNull
    @JsonProperty("providerId")
    var proveedorId: Int? = null,
    @JsonProperty("personIdentification")
    var identificacion: String? = null,
    @JsonProperty("personName")
    var nombre: String? = null,
    @JsonProperty("providerName")
    var nombreProveedor: String? = null,
    @JsonProperty("personEmail")
    var email: String? = null,
    @JsonProperty("personAddress")
    var direccion: String? = null,
    @JsonProperty("personPhone")
    var telefono: String? = null,
    @JsonProperty("retentionSourceId")
    var idRetencionFuente: Int? = null,
    @JsonProperty("retentionIVAId")
    var idRetencionIva: Int? = null,
    @JsonProperty("creditDays")
    var diasCredito: Int? = null,
    @JsonProperty("paymentConditionId")
    var idCondicionPago: Int? = null,
    @JsonProperty("expenseAccountId")
    var cuentaGastoProveedor: Int? = null,
    @JsonProperty("isForeign")
    var esExtranjero: Boolean? = null,
)
