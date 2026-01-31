package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.IdentificacionFiscalHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class IdentificacionFiscalRouter {

    @Bean
    fun taxIdentificationRouter(handler: IdentificacionFiscalHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.TAX_IDENTIFICATION, handler::create)
            .PUT(PathConstants.TAX_IDENTIFICATION + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.TAX_IDENTIFICATION + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.TAX_IDENTIFICATION, handler::getAll)
            .build()
}
