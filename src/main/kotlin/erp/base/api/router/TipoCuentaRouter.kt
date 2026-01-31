package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.TipoCuentaBancariaHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class TipoCuentaRouter {

    @Bean
    fun tipoCuentaRoutes(handler: TipoCuentaBancariaHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.ACCOUNT_TYPE, handler::create)
            .PUT(PathConstants.ACCOUNT_TYPE + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.ACCOUNT_TYPE + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.ACCOUNT_TYPE, handler::getAll)
            .build()
}
