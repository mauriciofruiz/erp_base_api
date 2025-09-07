package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TarjetaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TarjetaRouter {

    @Bean
    public RouterFunction<ServerResponse> cardRoutes(TarjetaHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.CARD, handler::getAllTarjetas)
                .build();
    }
}
