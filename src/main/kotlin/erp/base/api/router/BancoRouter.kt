package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.BancoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class BancoRouter {

    @Bean
    fun bankRouter(handler: BancoHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.BANK, handler::createBanck)
            .PUT(PathConstants.BANK + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.BANK + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.BANK, handler::getBanksBySearchParams)
            .GET(PathConstants.BANK + PathConstants.CONSULT + PathConstants.LIST, handler::getBankList)
            .GET(PathConstants.BANK + PathConstants.CONSULT + PathConstants.LIST + PathConstants.BRANCH, handler::getBankListByIdSucursal)
            .GET(PathConstants.BANK + PathConstants.CONSULT + PathConstants.LIST + PathConstants.BANK, handler::getBankListIsBank)
            .GET(PathConstants.BANK + PathConstants.LAST + PathConstants.CHECK, handler::getLastCheckBank)
            .build()
}
