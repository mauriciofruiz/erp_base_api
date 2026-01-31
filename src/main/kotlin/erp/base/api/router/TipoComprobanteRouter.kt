package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.TipoComprobanteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class TipoComprobanteRouter {

    @Bean
    fun voucherTypeRouter(handler: TipoComprobanteHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .GET(PathConstants.VOUCHER_TYPE, handler::getAll)
            .build()
}
