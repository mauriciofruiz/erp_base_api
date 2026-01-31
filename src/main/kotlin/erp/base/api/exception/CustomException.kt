package erp.base.api.exception

import org.springframework.http.HttpStatus

/**
 * Clase que maneja las excepciones personalizadas
 */
class CustomException(
    val status: HttpStatus,
    message: String?,
) : Exception(message)
