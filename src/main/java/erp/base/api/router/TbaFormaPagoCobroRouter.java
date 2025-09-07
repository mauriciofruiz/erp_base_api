package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaFormaPagoCobroHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaFormaPagoCobroRouter {

    @Bean
    public RouterFunction<ServerResponse> formaPagoCobroRouter(TbaFormaPagoCobroHandler tbaFormaPagoCobroHandler){
        return RouterFunctions.route()
                .POST(PathConstants.PAYMENT_METHOD_COLLECTION, tbaFormaPagoCobroHandler::createPaymentCollectionMethod)
                .PUT(PathConstants.PAYMENT_METHOD_COLLECTION +PathConstants.ID_PARAM, tbaFormaPagoCobroHandler::updatePaymentCollectionMethod)
                .GET(PathConstants.PAYMENT_METHOD_COLLECTION +PathConstants.ID_PARAM, tbaFormaPagoCobroHandler::getPaymentCollectionMethod)
                .GET(PathConstants.PAYMENT_METHOD_COLLECTION, tbaFormaPagoCobroHandler::getRequestBySearchParams)
                .build();
    }
}
