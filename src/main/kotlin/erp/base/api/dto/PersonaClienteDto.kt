package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import erp.base.api.models.CreditoCliente

data class PersonaClienteDto(
    @JsonProperty("person")
    val persona: PersonaDto,
    @JsonProperty("client")
    val cliente: ClienteDto,
    @JsonProperty("credit")
    val credito: CreditoCliente?,
    @JsonProperty("exceptions")
    val excepciones: List<ExcepcionesClienteDescDto>? = null,
)
