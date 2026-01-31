package erp.base.api.exception

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest

@Component
class CustomAttributes : DefaultErrorAttributes() {

    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): MutableMap<String, Any> {
        val errorAttributes = mutableMapOf<String, Any>()
        val throwable = getError(request)
        if (throwable is CustomException) {
            errorAttributes["status"] = throwable.status
            errorAttributes["message"] = (throwable.message ?: "")
        }
        return errorAttributes
    }
}
