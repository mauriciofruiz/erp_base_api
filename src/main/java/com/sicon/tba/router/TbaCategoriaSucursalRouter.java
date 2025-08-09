package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaCategoriaSucursalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaCategoriaSucursalRouter {

    /**
     * Método que contiene las rutas de las categorias de sucursal
     * @param handler Objeto con los servicios de las categorias de sucursal
     * @return  Objeto con las rutas de las categorias de sucursal
     */

    @Bean
    public RouterFunction<ServerResponse> branchCategoryRouter(TbaCategoriaSucursalHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.BRANCH_CATEGORY, handler::create)
                .PUT(PathConstants.BRANCH_CATEGORY + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.BRANCH_CATEGORY+ PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.BRANCH_CATEGORY, handler::getByBranchParams)
                .build();
    }
}
