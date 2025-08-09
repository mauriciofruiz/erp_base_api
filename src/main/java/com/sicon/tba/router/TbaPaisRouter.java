package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaPaisHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de pais.
 */
@Configuration
public class TbaPaisRouter {

    /**
     * Método que maneja las rutas de pais.
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    public RouterFunction<ServerResponse> countryRouter(TbaPaisHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.COUNTRY, handler::createCountry)
                .PUT(PathConstants.COUNTRY +PathConstants.ID_PARAM, handler::updateCountry)
                .GET(PathConstants.COUNTRY + PathConstants.ID_PARAM, handler::getCountryById)
                .GET(PathConstants.COUNTRY, handler::getAllCountries)
                .build();
    }
}
