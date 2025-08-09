package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaMenuUsuarioHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que contiene las rutas para el manejo de la tabla TBA_MENU_USUARIO
 */
@Configuration
public class TbaMenuUsuarioRouter {

    /**
     * Método que contiene las rutas para el manejo de la tabla TBA_MENU_USUARIO
     * @param handler Objeto que contiene los métodos para el manejo de la tabla TBA_MENU_USUARIO
     * @return  Objeto RouterFunction de ServerResponse
     */
    @Bean
    public RouterFunction<ServerResponse> userMenuRouter(TbaMenuUsuarioHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.MENU_USER, handler::create)
                .PUT(PathConstants.MENU_USER, handler::updateByList)
                .PUT(PathConstants.MENU_USER + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.MENU_USER + PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.MENU_USER , handler::getByRequestParams)
                .build();
    }
}
