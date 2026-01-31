package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.TipoContribuyenteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class TipoContribuyenteRouter {

    @Bean
    fun contributorTypeRouter(handler: TipoContribuyenteHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.CONTRIBUTOR_TYPE, handler::createContributorType)
            .PUT(PathConstants.CONTRIBUTOR_TYPE + PathConstants.ID_PARAM, handler::updateContributorType)
            .GET(PathConstants.CONTRIBUTOR_TYPE + PathConstants.ID_PARAM, handler::getContributorTypeById)
            .GET(PathConstants.CONTRIBUTOR_TYPE, handler::getAllContributorTypes)
            .build()
}
