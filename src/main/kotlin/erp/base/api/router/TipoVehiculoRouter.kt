package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.TipoVehiculoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class TipoVehiculoRouter {

    @Bean
    fun vehicleTypeRouter(handler: TipoVehiculoHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.VEHICLE_TYPE, handler::create)
            .GET(PathConstants.VEHICLE_TYPE + PathConstants.ID_PARAM, handler::findById)
            .GET(PathConstants.VEHICLE_TYPE, handler::findAll)
            .DELETE(PathConstants.VEHICLE_TYPE + PathConstants.ID_PARAM, handler::delete)
            .build()
}
