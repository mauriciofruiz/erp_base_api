package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.CiudadHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class CiudadRouter {

    @Bean
    fun cityRouter(handler: CiudadHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.CITY, handler::create)
            .PUT(PathConstants.CITY + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.CITY + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.CITY, handler::getByParams)
            .build()
}
