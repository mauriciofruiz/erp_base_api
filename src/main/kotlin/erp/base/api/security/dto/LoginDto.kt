package erp.base.api.security.dto

import jakarta.validation.constraints.NotBlank

data class LoginDto(
    @get:NotBlank(message = "El nick es requerido")
    var nick: String? = null,
    @get:NotBlank(message = "La contraseña es requerida")
    var password: String? = null
)
