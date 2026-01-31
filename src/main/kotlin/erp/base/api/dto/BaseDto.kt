package erp.base.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

abstract class BaseDto(
    @JsonProperty("creationUserId")
    open val idUsuarioCreacion: Int? = null,

    @JsonProperty("modificationUserId")
    open val idUsuarioModificacion: Int? = null,

    @JsonProperty("registrationDate")
    open val fechaCreacion: LocalDateTime? = null,

    @JsonProperty("modificationDate")
    open val fechaModificacion: LocalDateTime? = null,
)
