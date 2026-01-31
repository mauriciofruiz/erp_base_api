package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.FormaPagoCobroHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class FormaPagoCobroRouter {

    @Bean
    fun formaPagoCobroRoutes(handler: FormaPagoCobroHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.PAYMENT_METHOD_COLLECTION, handler::createPaymentCollectionMethod)
            .PUT(PathConstants.PAYMENT_METHOD_COLLECTION + PathConstants.ID_PARAM, handler::updatePaymentCollectionMethod)
            .GET(PathConstants.PAYMENT_METHOD_COLLECTION + PathConstants.ID_PARAM, handler::getPaymentCollectionMethod)
            .GET(PathConstants.PAYMENT_METHOD_COLLECTION, handler::getRequestBySearchParams)
            .build()
}
