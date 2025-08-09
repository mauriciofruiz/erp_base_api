package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TarjetaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TarjetaRouter {

    @Bean
    public RouterFunction<ServerResponse> cardRoutes(TarjetaHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.CARD, handler::getAllTarjetas)
                .build();
    }
}
