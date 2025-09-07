package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.PeriodoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PeriodoRouter {
    @Bean
    RouterFunction<ServerResponse> periodRouter(PeriodoHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.PERIOD, handler::getRequestBySearchParams)
                .POST(PathConstants.PERIOD, handler:: create)
                .build();
    }
}
