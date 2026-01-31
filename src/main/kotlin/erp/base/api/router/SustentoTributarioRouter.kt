package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.SustentoTributarioHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class SustentoTributarioRouter {

    @Bean
    fun sustentoRouter(handler: SustentoTributarioHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .GET(PathConstants.TAX_SUPPORT, handler::getAll)
            .build()
}
