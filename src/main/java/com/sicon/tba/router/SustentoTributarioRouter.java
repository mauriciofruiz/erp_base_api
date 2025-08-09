package com.sicon.tba.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.handler.SustentoTributarioHandler;
import com.sicon.tba.handler.TbaCiudadHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de la tabla TbaCiudad
 */
@Configuration
public class SustentoTributarioRouter {

    /**
     * Método que maneja las rutas de la tabla TbaSustentoTributario
     * @param handler objeto de tipo TbaSustentoTributario
     * @return objeto de tipo RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> sustentoRouter (SustentoTributarioHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.TAX_SUPPORT, handler::getAll)
                .build();
    }
}
