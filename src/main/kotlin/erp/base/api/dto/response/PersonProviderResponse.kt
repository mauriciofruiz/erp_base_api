package erp.base.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import erp.base.api.models.Persona
import erp.base.api.models.Proveedor

data class PersonProviderResponse(
    @JsonProperty("person")
    val persona: Persona,
    @JsonProperty("provider")
    val proveedor: Proveedor,
)
