package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.BitacoraHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BitacoraRouter {

    @Bean
    public RouterFunction<ServerResponse> bitacoraRouterFunction(BitacoraHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.TRANSACTION_LOG, handler::save)
                .GET(PathConstants.TRANSACTION_LOG, handler::findAllByIdAndTable)
                .GET(PathConstants.TRANSACTION_LOG + PathConstants.CLIENT + PathConstants.ID_PARAM, handler::findTransactionLogByClient)
                .build();
    }
}
