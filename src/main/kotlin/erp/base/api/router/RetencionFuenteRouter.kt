package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.RetencionFuenteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RetencionFuenteRouter {

    @Bean
    fun retencionFuenteRoutes(handler: RetencionFuenteHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.RETENTION_SOURCE, handler::createRetentionSource)
            .PUT(PathConstants.RETENTION_SOURCE + PathConstants.ID_PARAM, handler::updateRetentionSource)
            .GET(PathConstants.RETENTION_SOURCE + PathConstants.ID_PARAM, handler::getRetentionSourceById)
            .GET(PathConstants.RETENTION_SOURCE, handler::getAllRetentionSource)
            .GET(PathConstants.RETENTION_SOURCE_CLIENT, handler::getAllRetentionSourceClient)
            .build()
}
