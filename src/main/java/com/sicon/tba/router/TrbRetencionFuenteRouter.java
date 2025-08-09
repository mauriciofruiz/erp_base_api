package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TrbRetencionFuenteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TrbRetencionFuenteRouter {

    @Bean
    public RouterFunction<ServerResponse> retencionFuenteRouter(TrbRetencionFuenteHandler trbRetencionFuenteHandler) {
        return RouterFunctions.route()
                .POST(PathConstants.RETENTION_SOURCE, trbRetencionFuenteHandler::createRetentionSource)
                .PUT(PathConstants.RETENTION_SOURCE + PathConstants.ID_PARAM, trbRetencionFuenteHandler::updateRetentionSource)
                .GET(PathConstants.RETENTION_SOURCE + PathConstants.ID_PARAM, trbRetencionFuenteHandler::getRetentionSourceById)
                .GET(PathConstants.RETENTION_SOURCE, trbRetencionFuenteHandler::getAllRetentionSource)
                .GET(PathConstants.RETENTION_SOURCE_CLIENT, trbRetencionFuenteHandler::getAllRetentionSourceClient)
                .build();
    }
}
