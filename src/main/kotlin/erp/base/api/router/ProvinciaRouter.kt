package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.ProvinciaHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class ProvinciaRouter {

    @Bean
    fun provinceRouter(handler: ProvinciaHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.PROVINCE, handler::create)
            .PUT(PathConstants.PROVINCE + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.PROVINCE + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.PROVINCE, handler::getByParams)
            .build()
}
