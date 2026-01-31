package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.SesionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class SesionRouter {

    @Bean
    fun sesionRoutes(handler: SesionHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .GET(PathConstants.SESSION, handler::getBySearchParams)
            .PUT(PathConstants.SESSION + PathConstants.ID_PARAM, handler::updateStatusSesion)
            .PUT(PathConstants.SESSION, handler::updateAllStatusSesion)
            .build()
    }
}
