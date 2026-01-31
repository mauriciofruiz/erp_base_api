package erp.base.api.security.router

import erp.base.api.constants.PathConstants
import erp.base.api.security.handler.LoginHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class LoginRouter {

    @Bean
    fun routerLogin(handler: LoginHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.LOGIN, handler::login)
            .GET(PathConstants.LOGOUT, handler::logout)
            .GET(PathConstants.SESSION_STATE, handler::sessionState)
            .build()
}
