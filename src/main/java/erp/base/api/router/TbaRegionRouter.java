package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaRegionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class TbaRegionRouter {

    /**
     * Método que define las rutas para las regiones
     * @param handler manejador de regiones
     * @return  Un objeto TbaRegión
     */

    @Bean
    public RouterFunction<ServerResponse> regionRouter(TbaRegionHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.REGION, handler::create)
                .PUT(PathConstants.REGION + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.REGION + PathConstants.ID_PARAM, handler::getRegionById)
                .GET(PathConstants.REGION, handler::getRegionByName)
                .build();
    }
}
