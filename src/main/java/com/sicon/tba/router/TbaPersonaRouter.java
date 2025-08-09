package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaPersonaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de login
 */
@Configuration
public class TbaPersonaRouter {

    /**
     * Método que maneja las rutas de login
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    RouterFunction<ServerResponse> personRouter (TbaPersonaHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.PERSON, handler::create)
                .PUT(PathConstants.PERSON + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.PERSON + PathConstants.ID_PARAM, handler::getById)
                //.GET(PathConstants.PERSON , handler::getPersonsBySearchParams)
                .GET(PathConstants.PERSON + PathConstants.PROVIDER + PathConstants.ID_PARAM , handler::getPersonProvidersByProviderId)
                .GET(PathConstants.PROVIDER + PathConstants.PERSON, handler::getAllPersonProviders)
                .GET(PathConstants.PERSON + PathConstants.CLIENT + PathConstants.ID_PARAM , handler::getPersonClientByProviderId)
                .GET(PathConstants.CLIENT + PathConstants.PERSON + PathConstants.FICHA, handler::getAllPersonClients)
                .GET(PathConstants.PERSON, handler::getAllPeople)
                .build();
    }

}


