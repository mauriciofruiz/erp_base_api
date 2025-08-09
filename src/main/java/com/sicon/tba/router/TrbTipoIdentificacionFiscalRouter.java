package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TrbTipoContribuyenteHandler;
import com.sicon.tba.handler.TrbTipoIdentificacionFiscalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TrbTipoIdentificacionFiscalRouter {

    /**
     * Método que maneja las rutas de tipo contribuyente.
     * @param trbTipoIdentificacionFiscalHandler handler
     * @return RouterFunction
     */
    @Bean
    public RouterFunction<ServerResponse> fiscalIdentificationTypeRouter(TrbTipoIdentificacionFiscalHandler trbTipoIdentificacionFiscalHandler){
        return RouterFunctions.route()
                .POST(PathConstants.FISCAL_IDENTIFICATION_TYPE, trbTipoIdentificacionFiscalHandler::createFiscalIdentificationType)
                .PUT(PathConstants.FISCAL_IDENTIFICATION_TYPE+PathConstants.ID_PARAM, trbTipoIdentificacionFiscalHandler::updateFiscalIdentificationType)
                .GET(PathConstants.FISCAL_IDENTIFICATION_TYPE + PathConstants.ID_PARAM, trbTipoIdentificacionFiscalHandler::getFiscalIdentificationTypeById)
                .GET(PathConstants.FISCAL_IDENTIFICATION_TYPE, trbTipoIdentificacionFiscalHandler::getAllFiscalIdentificationType)
                .build();
    }
}
