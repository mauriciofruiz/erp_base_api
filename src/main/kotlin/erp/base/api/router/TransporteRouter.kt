package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.TransporteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class TransporteRouter {

    @Bean
    fun transportationRouter(handler: TransporteHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.TRANSPORTATION, handler::create)
            .PUT(PathConstants.TRANSPORTATION + PathConstants.ID_PARAM, handler::update)
            .PATCH(PathConstants.TRANSPORTATION + PathConstants.ID_PARAM, handler::patch)
            .GET(PathConstants.TRANSPORTATION + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.TRANSPORTATION, handler::getBySearchParams)
            .GET(PathConstants.TRANSPORTATION + PathConstants.ACTIVE + PathConstants.GET, handler::getAllActiveTransportistas)
            .GET(PathConstants.TRANSPORTATION + PathConstants.GET + PathConstants.PARAMS, handler::getTransportationByParams)
            .build()
}
