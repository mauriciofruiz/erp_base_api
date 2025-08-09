package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaGeneroHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de género.
 */
@Configuration
public class TbaGeneroRouter {

    /**
     * Método que maneja las rutas de género.
     * @return RouterFunction
     */
    @Bean
    public RouterFunction<ServerResponse> genderRouter(TbaGeneroHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.GENDER, handler::create)
                .PUT(PathConstants.GENDER +PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.GENDER+ PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.GENDER , handler::getAll)
                .build();
    }
}
