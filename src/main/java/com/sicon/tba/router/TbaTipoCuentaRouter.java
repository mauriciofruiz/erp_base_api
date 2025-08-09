package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaTipoCuentaBancariaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaTipoCuentaRouter {

    /**
     * Método que contiene las rutas de los tipos de cuentas
     * @param handler Objeto con los servicios de las provincias
     * @return  Objeto con las rutas de las provincias
     */
    @Bean
    public RouterFunction<ServerResponse> tipoCuentaRouter(TbaTipoCuentaBancariaHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.ACCOUNT_TYPE, handler::create)
                .PUT(PathConstants.ACCOUNT_TYPE + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.ACCOUNT_TYPE + PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.ACCOUNT_TYPE, handler::getAll)
                .build();
    }
}
