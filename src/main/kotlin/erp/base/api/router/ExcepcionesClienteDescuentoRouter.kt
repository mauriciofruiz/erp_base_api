package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.ExcepcionesClienteDescuentoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class ExcepcionesClienteDescuentoRouter {

    @Bean
    fun customerDiscountExceptionsRouter(handler: ExcepcionesClienteDescuentoHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .DELETE(PathConstants.EXCEPTIONS_CUSTOMER_DISCOUNT + PathConstants.ID_PARAM, handler::delete)
            .GET(PathConstants.EXCEPTIONS_CUSTOMER_DISCOUNT, handler::getByParams)
            .build()
}
