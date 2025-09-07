package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.AdquirienteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AdquirienteRouter {

    @Bean
    public RouterFunction<ServerResponse> acquirerRoutes(AdquirienteHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.ACQUIRER, handler::getAllByActiveEstado)
                .build();
    }
}
