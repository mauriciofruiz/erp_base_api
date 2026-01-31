package erp.base.api.validation

import erp.base.api.exception.CustomException
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ObjectValidator(
    private val validator: Validator,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun <T> validate(object_: T): T {
        val errors: Set<ConstraintViolation<T>> = validator.validate(object_)
        return if (errors.isEmpty()) {
            object_
        } else {
            val message = errors.joinToString(", ") { it.message }
            throw CustomException(HttpStatus.BAD_REQUEST, message)
        }
    }
}
