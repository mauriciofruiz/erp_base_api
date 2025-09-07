package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaCajaHandler;
import erp.base.api.handler.TbaPersonaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de Caja
 */
@Configuration
public class TbaCajaRouter {
    /**
     * Método que maneja las rutas de Caja
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    RouterFunction<ServerResponse> cajaRouter (TbaCajaHandler handler){
        return RouterFunctions.route()
                .GET(PathConstants.BOX + PathConstants.BRANCH + PathConstants.ID_PARAM, handler::findCajaByIdSucusal)
                .GET(PathConstants.BOX + PathConstants.ID_PARAM, handler::findCajaById)
                .GET(PathConstants.BOX, handler::getRequestBySearchParams)
                .POST(PathConstants.BOX, handler::create)
                .PUT(PathConstants.BOX + PathConstants.ID_PARAM, handler::update)
                .build();
    }
}
