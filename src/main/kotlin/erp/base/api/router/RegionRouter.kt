package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.RegionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RegionRouter {

    @Bean
    fun regionRoutes(handler: RegionHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.REGION, handler::create)
            .PUT(PathConstants.REGION + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.REGION + PathConstants.ID_PARAM, handler::getRegionById)
            .GET(PathConstants.REGION, handler::getRegionByName)
            .build()
}
