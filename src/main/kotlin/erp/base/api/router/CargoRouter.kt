package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.CargoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class CargoRouter {

    @Bean
    fun positionRouter(handler: CargoHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.POSITION, handler::create)
            .PUT(PathConstants.POSITION + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.POSITION + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.POSITION, handler::getAll)
            .build()
}
