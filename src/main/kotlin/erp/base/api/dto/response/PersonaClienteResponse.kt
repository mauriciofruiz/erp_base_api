package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PersonaClienteResponse(
    @field:NotNull
    @JsonProperty("personId")
    var personaId: Int? = null,
    @field:NotNull
    @JsonProperty("clientId")
    var clienteId: Int? = null,
    @JsonProperty("personIdentification")
    var identificacion: String? = null,
    @JsonProperty("personName")
    var nombre: String? = null,
    @JsonProperty("clientName")
    var nombreCliente: String? = null,
    @JsonProperty("personEmail")
    var email: String? = null,
    @JsonProperty("authorizedQuota")
    var cupoAutorizado: BigDecimal? = null,
    @JsonProperty("usedQuota")
    var cupoUtilizado: BigDecimal? = null,
    @JsonProperty("overdraft")
    var sobreCupo: BigDecimal? = null,
    @JsonProperty("availableQuota")
    var cupoDisponible: BigDecimal? = null,
    @JsonProperty("clientCategory")
    var categoriaCliente: String? = null,
    @JsonProperty("clientContact")
    var contactoCliente: String? = null,
    @JsonProperty("personPhone")
    var telefono: String? = null,
    @JsonProperty("creditDays")
    var diasCredito: Int? = null,
    @JsonProperty("creditObservation")
    var observacion: String? = null,
    @JsonProperty("plate")
    var placa: String? = null,
    @JsonProperty("taxIdentificationCode")
    var codigoIdentificacionTributaria: String? = null,
)
