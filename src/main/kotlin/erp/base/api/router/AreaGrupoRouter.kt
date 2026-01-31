package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.AreaGrupoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class AreaGrupoRouter {

    @Bean
    fun areaGroupRoutes(handler: AreaGrupoHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.GROUP_AREA, handler::create)
            .PUT(PathConstants.GROUP_AREA + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.GROUP_AREA + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.GROUP_AREA, handler::getByParams)
            .build()
}
