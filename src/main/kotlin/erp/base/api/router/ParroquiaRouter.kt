package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.ParroquiaHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class ParroquiaRouter {

    @Bean
    fun parishRouter(handler: ParroquiaHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.PARISH, handler::createParish)
            .PUT(PathConstants.PARISH + PathConstants.ID_PARAM, handler::updateParish)
            .GET(PathConstants.PARISH + PathConstants.ID_PARAM, handler::getParishById)
            .GET(PathConstants.PARISH + PathConstants.CITY + PathConstants.ID_PARAM, handler::getParishByCityId)
            .GET(PathConstants.PARISH, handler::getParish)
            .GET(PathConstants.PARISH, handler::getAllParishes)
            .build()
}
