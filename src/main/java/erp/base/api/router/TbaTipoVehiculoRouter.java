package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaTipoVehiculoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaTipoVehiculoRouter {

    @Bean
    RouterFunction<ServerResponse> vehicleTypeRouter(TbaTipoVehiculoHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.VEHICLE_TYPE, handler::create)
                .GET(PathConstants.VEHICLE_TYPE + PathConstants.ID_PARAM, handler::findById)
                .GET(PathConstants.VEHICLE_TYPE, handler::findAll)
                .DELETE(PathConstants.VEHICLE_TYPE + PathConstants.ID_PARAM, handler::delete)
                .build();
    }
}
