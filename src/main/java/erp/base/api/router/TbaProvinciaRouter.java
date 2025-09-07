package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaProvinciaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que contiene las rutas de las provincias
 */
@Configuration
public class TbaProvinciaRouter {

    /**
     * Método que contiene las rutas de las provincias
     * @param handler Objeto con los servicios de las provincias
     * @return  Objeto con las rutas de las provincias
     */
    @Bean
    public RouterFunction<ServerResponse> provinceRouter(TbaProvinciaHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.PROVINCE, handler::create)
                .PUT(PathConstants.PROVINCE  + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.PROVINCE + PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.PROVINCE, handler::getByParams)
                .build();
    }
}
