package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TrbIdentificacionFiscalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de identificación fiscal.
 */
@Configuration
public class TbaIdentificacionFiscalRouter {

    /**
     * Método que maneja las rutas de identificación fiscal.
     * @param handler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> taxIdentificationRouter(TrbIdentificacionFiscalHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.TAX_IDENTIFICATION, handler::create)
                .PUT(PathConstants.TAX_IDENTIFICATION + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.TAX_IDENTIFICATION + PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.TAX_IDENTIFICATION, handler::getAll)
                .build();
    }
}
