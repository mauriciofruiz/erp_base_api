package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.AdquirienteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AdquirienteRouter {

    @Bean
    public RouterFunction<ServerResponse> acquirerRoutes(AdquirienteHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.ACQUIRER, handler::getAllByActiveEstado)
                .build();
    }
}
