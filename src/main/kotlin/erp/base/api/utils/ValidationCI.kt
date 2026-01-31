package erp.base.api.utils

import erp.base.api.constants.ErrorMessage
import erp.base.api.exception.CustomException
import org.springframework.http.HttpStatus

object ValidationCI {

    /**
     * Validacion del valor Inicial
     */
    @JvmStatic
    fun validateInitial(numero: String?, caracteres: Int): Boolean {
        if (numero.isNullOrBlank()) {
            throw CustomException(HttpStatus.NO_CONTENT, ErrorMessage.CI_ERROR)
        }
        if (!numero.all { it.isDigit() }) {
            throw Exception("Valor ingresado sslo puede tener digitos")
        }
        if (numero.length != caracteres) {
            throw Exception("Valor ingresado debe tener $caracteres")
        }
        return true
    }

    /**
     * Validacion cod de provincia
     */
    @JvmStatic
    fun validateCodProvince(numero: String): Boolean {
        val n = numero.toIntOrNull() ?: throw CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR)
        if (n < 0 || n > 24) {
            throw CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR)
        }
        return true
    }

    /**
     * Validación tercer digito
     */
    @JvmStatic
    fun validateThirdDigit(numero: String, tipo: Int?): Boolean {
        when (tipo) {
            1, 2 -> {
                val n = numero.toIntOrNull() ?: throw CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR)
                if (n < 0 || n > 5) throw CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR)
            }
            3 -> {
                if (numero.toIntOrNull() != 9) throw CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR)
            }
            4 -> {
                if (numero.toIntOrNull() != 6) throw CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_ERROR)
            }
            else -> throw CustomException(HttpStatus.CONFLICT, ErrorMessage.CI_NOT_EXISTS)
        }
        return true
    }
}
