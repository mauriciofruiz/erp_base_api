package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.EstadoCivilHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class EstadoCivilRouter {

    @Bean
    fun maritalStatusRouter(handler: EstadoCivilHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.MARITAL_STATUS, handler::createMaritalStatus)
            .PUT(PathConstants.MARITAL_STATUS + PathConstants.ID_PARAM, handler::updateMaritalStatus)
            .GET(PathConstants.MARITAL_STATUS + PathConstants.ID_PARAM, handler::getMaritalStatusById)
            .GET(PathConstants.MARITAL_STATUS, handler::getAllMaritalStatus)
            .build()
}
