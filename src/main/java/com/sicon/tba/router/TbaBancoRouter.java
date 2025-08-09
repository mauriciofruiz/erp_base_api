package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TbaBancoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaBancoRouter {

    /**
     * Método que maneja las rutas de Banco.
     *
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    RouterFunction<ServerResponse> bankRouter(TbaBancoHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.BANK, handler::createBanck)
                .PUT(PathConstants.BANK + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.BANK + PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.BANK, handler::getBanksBySearchParams)
                .GET(PathConstants.BANK + PathConstants.CONSULT + PathConstants.LIST, handler::getBankList)
                .GET(PathConstants.BANK + PathConstants.CONSULT + PathConstants.LIST + PathConstants.BRANCH, handler::getBankListByIdSucursal)
                .GET(PathConstants.BANK + PathConstants.CONSULT + PathConstants.LIST + PathConstants.BANK, handler::getBankListIsBank)
                .GET(PathConstants.BANK + PathConstants.LAST + PathConstants.CHECK, handler::getLastCheckBank)
                .build();
    }
}
