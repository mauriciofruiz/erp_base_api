package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.CategoriaClienteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class CategoriaClienteRouter {

    @Bean
    fun categoryClientRouter(handler: CategoriaClienteHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .GET(PathConstants.CATEGORY_CLIENT, handler::getRequestBySearchParams)
            .POST(PathConstants.CATEGORY_CLIENT, handler::create)
            .PUT(PathConstants.CATEGORY_CLIENT + PathConstants.ID_PARAM, handler::update)
            .build()
}
