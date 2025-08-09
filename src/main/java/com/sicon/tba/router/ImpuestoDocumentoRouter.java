package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.ImpuestoDocumentoHandler;
import com.sicon.tba.handler.RolHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ImpuestoDocumentoRouter {

    @Bean
    public RouterFunction<ServerResponse> impuestoDocumentoRouterFunction(ImpuestoDocumentoHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.DOCUMENT_TAX, handler::findImpuestosByDocumento)
                .build();
    }
}
