package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.TipoProveedorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class TipoProveedorRouter {

    @Bean
    fun vendorTypeRouter(handler: TipoProveedorHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.PROVIDER_TYPE, handler::createVendorType)
            .GET(PathConstants.PROVIDER_TYPE + PathConstants.ID_PARAM, handler::getVendorTypeById)
            .GET(PathConstants.PROVIDER_TYPE, handler::getAllVendorTypes)
            .DELETE(PathConstants.PROVIDER_TYPE + PathConstants.ID_PARAM, handler::deleteVendorType)
            .build()
}
