package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.MenuHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class MenuRouter {

    @Bean
    fun menuRoutes(handler: MenuHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.MENU, handler::create)
            .PUT(PathConstants.MENU + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.MENU + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.MENU, handler::getRequestBySearchParams)
            .build()
}
