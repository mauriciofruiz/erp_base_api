package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.UsuarioHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class UsuarioRouter {

    @Bean
    fun userRouter(usuarioHandler: UsuarioHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .PUT(PathConstants.USER + PathConstants.ID_PARAM, usuarioHandler::updateuser)
            .GET(PathConstants.USER + PathConstants.ID_PARAM, usuarioHandler::getById)
            .GET(PathConstants.USER, usuarioHandler::getUsersBySearchParams)
            .PUT(PathConstants.USER, usuarioHandler::forgotPassword)
            .GET(PathConstants.PURCHASE_REQUEST + PathConstants.USER, usuarioHandler::getUsersRequestPurchase)
            .GET(PathConstants.PURCHASE_ORDER + PathConstants.USER, usuarioHandler::getUsersPurchaseOrder)
            .GET(PathConstants.SALE + PathConstants.USER, usuarioHandler::getSalesUsers)
            .build()
    }
}
