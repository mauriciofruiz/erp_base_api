package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.CategoriaSucursalHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class CategoriaSucursalRouter {

    @Bean
    fun branchCategoryRouter(handler: CategoriaSucursalHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.BRANCH_CATEGORY, handler::create)
            .PUT(PathConstants.BRANCH_CATEGORY + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.BRANCH_CATEGORY + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.BRANCH_CATEGORY, handler::getByBranchParams)
            .build()
}
