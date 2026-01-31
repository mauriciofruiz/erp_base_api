package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.RetencionIvaHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RetencionIvaRouter {

    @Bean
    fun retentionIvaRouter(handler: RetencionIvaHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.RETENTION_IVA, handler::createRetentionIva)
            .PUT(PathConstants.RETENTION_IVA + PathConstants.ID_PARAM, handler::updateRetentionIva)
            .GET(PathConstants.RETENTION_IVA + PathConstants.ID_PARAM, handler::getRetentionIva)
            .GET(PathConstants.RETENTION_IVA, handler::getAllRetentionIva)
            .GET(PathConstants.RETENTION_IVA_CLIENT, handler::getAllRetentionIvaCliente)
            .build()
}
