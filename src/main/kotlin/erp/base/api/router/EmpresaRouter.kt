package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.EmpresaHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class EmpresaRouter {

    @Bean
    fun companyRouter(handler: EmpresaHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .GET(PathConstants.COMPANY + PathConstants.ID_PARAM, handler::getCompany)
            .build()
}
