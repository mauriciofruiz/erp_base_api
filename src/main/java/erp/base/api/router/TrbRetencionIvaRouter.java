package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TrbRetencionIvaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TrbRetencionIvaRouter {

    @Bean
    public RouterFunction<ServerResponse> retentionIvaRouter(TrbRetencionIvaHandler trbRetencionIvaHandler){
        return RouterFunctions.route()
                .POST(PathConstants.RETENTION_IVA, trbRetencionIvaHandler::createRetentionIva)
                .PUT(PathConstants.RETENTION_IVA + PathConstants.ID_PARAM, trbRetencionIvaHandler::updateRetentionIva)
                .GET(PathConstants.RETENTION_IVA + PathConstants.ID_PARAM, trbRetencionIvaHandler::getRetentionIva)
                .GET(PathConstants.RETENTION_IVA, trbRetencionIvaHandler::getAllRetentionIva)
                .GET(PathConstants.RETENTION_IVA_CLIENT, trbRetencionIvaHandler::getAllRetentionIvaCliente)
                .build();
    }
}
