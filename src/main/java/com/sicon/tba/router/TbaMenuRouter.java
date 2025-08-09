package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaMenuHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de menu.
 */
@Configuration
public class TbaMenuRouter {

    /**
     * Método que maneja las rutas de menu.
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    RouterFunction<ServerResponse> menuRouter(TbaMenuHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.MENU, handler::create)
                .PUT(PathConstants.MENU + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.MENU + PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.MENU, handler::getRequestBySearchParams)
                .build();
    }
}
