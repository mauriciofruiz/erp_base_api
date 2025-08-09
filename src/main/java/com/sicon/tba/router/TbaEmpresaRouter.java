package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaEmpresaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaEmpresaRouter {

    @Bean
    public RouterFunction<ServerResponse> companyRouter(TbaEmpresaHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.COMPANY + PathConstants.ID_PARAM, handler::getCompany)
                .build();
    }
}
