package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.RolUsuarioHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RolUsuarioRouter {

    @Bean
    fun userRoleRouter(handler: RolUsuarioHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.USER_ROLE, handler::create)
            .PUT(PathConstants.USER_ROLE + PathConstants.ID_PARAM, handler::update)
            .DELETE(PathConstants.USER_ROLE + PathConstants.ID_PARAM, handler::delete)
            .GET(PathConstants.USER_ROLE, handler::findAll)
            .GET(PathConstants.USER_ROLE + PathConstants.ID_PARAM, handler::findRolByUserId)
            .build()
}
