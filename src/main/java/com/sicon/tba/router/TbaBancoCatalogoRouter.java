package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaBancoCatalogoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class
TbaBancoCatalogoRouter {

    @Bean
    public RouterFunction bankCatalogRouter(TbaBancoCatalogoHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.CATALOG_BANK, handler::getCatalogBanksBySearchParams)
                .POST(PathConstants.CATALOG_BANK, handler::create)
                .PUT(PathConstants.CATALOG_BANK + PathConstants.ID_PARAM, handler::update)
                .build();
    }
}
