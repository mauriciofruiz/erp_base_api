package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.ClienteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class ClienteRouter {

    @Bean
    fun clienteRoutes(handler: ClienteHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.CLIENT, handler::create)
            .PUT(PathConstants.CLIENT + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.CLIENT + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.CLIENT, handler::getByParams)
            .GET(PathConstants.CLIENT + PathConstants.VERIFY_EXISTENCE + PathConstants.ID_PARAM, handler::verifyClientExistsByIdentification)
            .build()
}
