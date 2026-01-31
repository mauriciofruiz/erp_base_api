package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.GeneroHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class GeneroRouter {

    @Bean
    fun genderRouter(handler: GeneroHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.GENDER, handler::create)
            .PUT(PathConstants.GENDER + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.GENDER + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.GENDER, handler::getAll)
            .build()
}
