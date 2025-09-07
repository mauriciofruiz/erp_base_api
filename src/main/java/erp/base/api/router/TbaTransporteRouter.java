package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaTransporteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaTransporteRouter {

    @Bean
    RouterFunction<ServerResponse> transportationRouter(TbaTransporteHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.TRANSPORTATION, handler::create)
                .PUT(PathConstants.TRANSPORTATION + PathConstants.ID_PARAM, handler::update)
                .PATCH(PathConstants.TRANSPORTATION + PathConstants.ID_PARAM, handler::patch)
                .GET(PathConstants.TRANSPORTATION + PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.TRANSPORTATION, handler::getBySearchParams)
                .GET(PathConstants.TRANSPORTATION + PathConstants.ACTIVE + PathConstants.GET, handler::getAllActiveTransportistas)
                .GET(PathConstants.TRANSPORTATION + PathConstants.GET + PathConstants.PARAMS, handler::getTransportationByParams)
                .build();
    }
}
