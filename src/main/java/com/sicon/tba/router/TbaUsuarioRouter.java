package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaUsuarioHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de usuario.
 */
@Configuration
public class TbaUsuarioRouter {

    /**
     * Método que maneja las rutas de usuario.
     * @param tbaUsuarioHandler handler
     * @return RouterFunction
     */
    @Bean
    RouterFunction<ServerResponse> userRouter (TbaUsuarioHandler tbaUsuarioHandler){
        return RouterFunctions.route()
                .PUT(PathConstants.USER + PathConstants.ID_PARAM, tbaUsuarioHandler::updateuser)
                .GET(PathConstants.USER + PathConstants.ID_PARAM, tbaUsuarioHandler::getById)
                .GET(PathConstants.USER , tbaUsuarioHandler::getUsersBySearchParams)
                .PUT(PathConstants.USER,tbaUsuarioHandler::forgotPassword )
                .GET(PathConstants.PURCHASE_REQUEST + PathConstants.USER, tbaUsuarioHandler::getUsersRequestPurchase)
                .GET(PathConstants.PURCHASE_ORDER + PathConstants.USER, tbaUsuarioHandler::getUsersPurchaseOrder)
                .GET(PathConstants.SALE + PathConstants.USER, tbaUsuarioHandler::getSalesUsers)
                .build();
    }
}
