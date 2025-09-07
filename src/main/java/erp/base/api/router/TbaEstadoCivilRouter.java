package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaEstadoCivilHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de estado civil.
 */
@Configuration
public class TbaEstadoCivilRouter {

    /**
     * Método que maneja las rutas de estado civil.
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    public RouterFunction<ServerResponse> maritalStatusRouter(TbaEstadoCivilHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.MARITAL_STATUS, handler::createMaritalStatus)
                .PUT(PathConstants.MARITAL_STATUS +PathConstants.ID_PARAM, handler::updateMaritalStatus)
                .GET(PathConstants.MARITAL_STATUS + PathConstants.ID_PARAM, handler::getMaritalStatusById)
                .GET(PathConstants.MARITAL_STATUS, handler::getAllMaritalStatus)
                .build();
    }
}
