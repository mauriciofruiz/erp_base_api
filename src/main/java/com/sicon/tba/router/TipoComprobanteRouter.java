package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.TipoComprobanteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de la tabla TbaCiudad
 */
@Configuration
public class TipoComprobanteRouter {

    /**
     * Método que maneja las rutas de la tabla TrbSustentoTributario
     *
     * @param handler objeto de tipo TrbSustentoTributario
     * @return objeto de tipo RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> voucherTypeRouter(TipoComprobanteHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.VOUCHER_TYPE, handler::getAll)
                .build();
    }
}
