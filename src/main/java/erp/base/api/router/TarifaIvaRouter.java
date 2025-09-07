package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TarifaIvaHandler;
import erp.base.api.handler.TrbRetencionFuenteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TarifaIvaRouter {

    @Bean
    public RouterFunction<ServerResponse> tarifaIvaRouterFunction (TarifaIvaHandler tarifaIvaHandler) {
        return RouterFunctions.route()
                .POST(PathConstants.TAX_RATE, tarifaIvaHandler::create)
                .PUT(PathConstants.TAX_RATE + PathConstants.ID_PARAM, tarifaIvaHandler::update)
                .GET(PathConstants.TAX_RATE + PathConstants.ID_PARAM, tarifaIvaHandler::getById)
                .GET(PathConstants.TAX_RATE, tarifaIvaHandler::getAllActives)
                .build();
    }
}
