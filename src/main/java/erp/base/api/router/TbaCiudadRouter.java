package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaCiudadHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de la tabla TbaCiudad
 */
@Configuration
public class TbaCiudadRouter {

    /**
     * Método que maneja las rutas de la tabla TbaCiudad
     * @param handler objeto de tipo TbaCiudadHandler
     * @return objeto de tipo RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> cityRouter(TbaCiudadHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.CITY, handler::create)
                .PUT(PathConstants.CITY + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.CITY+ PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.CITY, handler::getByParams)
                .build();
    }
}
