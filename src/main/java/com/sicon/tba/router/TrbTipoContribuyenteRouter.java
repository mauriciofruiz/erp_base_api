package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TrbTipoContribuyenteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TrbTipoContribuyenteRouter {

    /**
     * Método que maneja las rutas de tipo contribuyente.
     * @param trbTipoContribuyenteHandler handler
     * @return RouterFunction
     */
    @Bean
    public RouterFunction<ServerResponse> contributorTypeRouter(TrbTipoContribuyenteHandler trbTipoContribuyenteHandler){
        return RouterFunctions.route()
                .POST(PathConstants.CONTRIBUTOR_TYPE, trbTipoContribuyenteHandler::createContributorType)
                .PUT(PathConstants.CONTRIBUTOR_TYPE+PathConstants.ID_PARAM, trbTipoContribuyenteHandler::updateContributorType)
                .GET(PathConstants.CONTRIBUTOR_TYPE + PathConstants.ID_PARAM, trbTipoContribuyenteHandler::getContributorTypeById)
                .GET(PathConstants.CONTRIBUTOR_TYPE, trbTipoContribuyenteHandler::getAllContributorTypes)
                .build();
    }
}
