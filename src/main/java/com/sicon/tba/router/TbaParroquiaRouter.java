package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaParroquiaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de parroquia.
 */
@Configuration
public class TbaParroquiaRouter {

    /**
     * Método que maneja las rutas de parroquia.
     *
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    public RouterFunction<ServerResponse> parishRouter(TbaParroquiaHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.PARISH, handler::createParish)
                .PUT(PathConstants.PARISH + PathConstants.ID_PARAM, handler::updateParish)
                .GET(PathConstants.PARISH + PathConstants.ID_PARAM, handler::getParishById)
                .GET(PathConstants.PARISH + PathConstants.CITY + PathConstants.ID_PARAM, handler::getParishByCityId)
                .GET(PathConstants.PARISH, handler::getParish)
                .GET(PathConstants.PARISH, handler::getAllParishes)
                .build();
    }
}
