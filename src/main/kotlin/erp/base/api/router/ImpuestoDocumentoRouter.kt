package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.ImpuestoDocumentoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class ImpuestoDocumentoRouter {

    @Bean
    fun impuestoDocumentoRouterFunction(handler: ImpuestoDocumentoHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .GET(PathConstants.DOCUMENT_TAX, handler::findImpuestosByDocumento)
            .build()
}
