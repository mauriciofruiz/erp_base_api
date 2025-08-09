package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaDepartamentoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaDepartamentoRouter {

    @Bean
    public RouterFunction<ServerResponse> deparmentRouter(TbaDepartamentoHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.DEPARTMENT, handler::create)
                .PUT(PathConstants.DEPARTMENT + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.DEPARTMENT+ PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.DEPARTMENT, handler::getByParams)
                .GET(PathConstants.DEPARTMENT, handler::getAllDepartment)
                .build();

    }
}
