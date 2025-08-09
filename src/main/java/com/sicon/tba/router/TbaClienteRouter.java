package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaClienteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaClienteRouter {

    /**
     * Método que contiene las rutas de los clientes
     *
     * @param handler Objeto con los servicios de los clientes
     * @return Objeto con las rutas de los clientes
     */

    @Bean
    public RouterFunction<ServerResponse> clienteRouter(TbaClienteHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.CLIENT, handler::create)
                .PUT(PathConstants.CLIENT + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.CLIENT + PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.CLIENT, handler::getByParams)
                .GET(PathConstants.CLIENT + PathConstants.VERIFY_EXISTENCE + PathConstants.ID_PARAM, handler::verifyClientExistsByIdentification)
                .build();
    }
}
