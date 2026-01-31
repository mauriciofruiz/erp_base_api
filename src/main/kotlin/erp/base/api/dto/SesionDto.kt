package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SesionDto(
    @JsonProperty("sessionId")
    var idSesion: Int? = null,
    @JsonProperty("userId")
    var idUsr: Int? = null,
    @JsonProperty("nick")
    var nick_usr: String? = null,
    @JsonProperty("personName")
    var nombre_persona: String? = null,
    @JsonProperty("state")
    var estado: Boolean? = null,
)
