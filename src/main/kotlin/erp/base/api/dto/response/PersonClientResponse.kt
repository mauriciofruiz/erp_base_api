package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import erp.base.api.dto.ExcepcionesClienteDescDto
import erp.base.api.models.Cliente
import erp.base.api.models.CreditoCliente
import erp.base.api.models.Persona

data class PersonClientResponse(
    @JsonProperty("person")
    val persona: Persona,
    @JsonProperty("client")
    val cliente: Cliente,
    @JsonProperty("credit")
    val creditoCliente: CreditoCliente?,
    @JsonProperty("exceptions")
    val excepciones: List<ExcepcionesClienteDescDto>? = null,
)
