package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PersonaProveedorDto(
    @JsonProperty("person")
    val persona: PersonaDto,
    @JsonProperty("provider")
    val proveedor: ProveedorDto,
)
