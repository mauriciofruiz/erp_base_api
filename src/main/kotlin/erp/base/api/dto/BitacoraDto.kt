package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

data class BitacoraDto(
    @JsonProperty("logId")
    var id: Int? = null,
    @JsonProperty("userId")
    var idUsuario: Int? = null,
    @JsonProperty("userName")
    var nombreUsuario: String? = null,
    @JsonProperty("identifier")
    var identificador: Int? = null,
    @JsonProperty("table")
    var tabla: String? = null,
    @JsonProperty("field")
    var campo: String? = null,
    @JsonProperty("oldValue")
    var oldValue: String? = null,
    @JsonProperty("newValue")
    var newValue: String? = null,
    @JsonProperty("date")
    var fecha: ZonedDateTime? = null,
    @JsonProperty("deviceIp")
    var ip: String? = null,
    @JsonProperty("deviceName")
    var dispositivo: String? = null,
)
