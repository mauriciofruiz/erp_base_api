package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaTipoProveedorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaTipoProveedorRouter {

    @Bean
    RouterFunction<ServerResponse> vendorTypeRouter (TbaTipoProveedorHandler tbaTipoProveedorHandler){
        return RouterFunctions.route()
                .POST(PathConstants.PROVIDER_TYPE, tbaTipoProveedorHandler::createVendorType)
                .GET(PathConstants.PROVIDER_TYPE + PathConstants.ID_PARAM, tbaTipoProveedorHandler::getVendorTypeById)
                .GET(PathConstants.PROVIDER_TYPE, tbaTipoProveedorHandler::getAllVendorTypes)
                .DELETE(PathConstants.PROVIDER_TYPE + PathConstants.ID_PARAM, tbaTipoProveedorHandler::deleteVendorType)
                .build();
    }
}
