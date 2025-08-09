package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaSesionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaSesionRouter {

    @Bean
    RouterFunction<ServerResponse> sesionRouter(TbaSesionHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.SESSION, handler::getBySearchParams)
                .PUT(PathConstants.SESSION + PathConstants.ID_PARAM, handler::updateStatusSesion)
                .PUT(PathConstants.SESSION, handler::updateAllStatusSesion)
                .build();
    }
}
