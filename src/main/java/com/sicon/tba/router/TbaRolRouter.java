package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaRolHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaRolRouter {

    @Bean
    public RouterFunction<ServerResponse> roleRouter(TbaRolHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.ROLE, handler::createRole)
                .PUT(PathConstants.ROLE + PathConstants.ID_PARAM, handler::updateRole)
                .DELETE(PathConstants.ROLE + PathConstants.ID_PARAM, handler::deleteRole)
                .GET(PathConstants.ROLE + PathConstants.ID_PARAM, handler::getRoleById)
                .GET(PathConstants.ROLE, handler::getBySearchParams)
                .build();
    }
}
