package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.MenuUsuarioHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class MenuUsuarioRouter {

    @Bean
    fun userMenuRouter(handler: MenuUsuarioHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .POST(PathConstants.MENU_USER, handler::create)
            .PUT(PathConstants.MENU_USER, handler::updateByList)
            .PUT(PathConstants.MENU_USER + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.MENU_USER + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.MENU_USER, handler::getByRequestParams)
            .build()
    }
}
