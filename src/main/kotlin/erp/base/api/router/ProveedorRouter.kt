package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.ProveedorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class ProveedorRouter {

    @Bean
    fun providerRouter(handler: ProveedorHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .POST(PathConstants.PROVIDER, handler::createProvider)
            .PUT(PathConstants.PROVIDER + PathConstants.ID_PARAM, handler::updateProvider)
            .PATCH(PathConstants.PROVIDER + PathConstants.ID_PARAM, handler::patchProvider)
            .GET(PathConstants.PROVIDER, handler::getAllProvider)
            .GET(PathConstants.PROVIDER + PathConstants.BY_PARAMETER + PathConstants.ID_PARAM, handler::getProvider)
            .GET(PathConstants.PROVIDER + PathConstants.PURCHASE_REQUEST, handler::getProvidersNameOCPendings)
            .GET(PathConstants.PROVIDER + PathConstants.ORDER_LIST, handler::getAllByListaPedidoAprobadas)
            .GET(PathConstants.PROVIDER + PathConstants.IMPORT + PathConstants.ID_PARAM, handler::getProviderForImport)
            .GET(PathConstants.PROVIDER + PathConstants.VERIFY_EXISTENCE + PathConstants.ID_PARAM, handler::verifyProviderExistsByIdentification)
            .GET(PathConstants.PROVIDER + PathConstants.CARRIER, handler::getCarriers)
            .build()
    }
}
