package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaProyectoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de login
 */
@Configuration
public class TbaProyectoRouter {

    /**
     * Método que maneja las rutas de login
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    RouterFunction<ServerResponse> projectRouter (TbaProyectoHandler handler){
        return RouterFunctions.route()
                .GET(PathConstants.PROJECT, handler::getAllProjectsActive)
                .build();
    }
}


