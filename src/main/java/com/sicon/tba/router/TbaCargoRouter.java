package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaCargoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de cargo.
 */
@Configuration
public class TbaCargoRouter {

    /**
     * Método que maneja las rutas de cargo.
     *
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    RouterFunction<ServerResponse> positionRouter(TbaCargoHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.POSITION, handler::create)
                .PUT(PathConstants.POSITION+ PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.POSITION+ PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.POSITION, handler::getAll)
                .build();
    }

}
