package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.ImpuestoDocumentoHandler;
import erp.base.api.handler.RolHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ImpuestoDocumentoRouter {

    @Bean
    public RouterFunction<ServerResponse> impuestoDocumentoRouterFunction(ImpuestoDocumentoHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.DOCUMENT_TAX, handler::findImpuestosByDocumento)
                .build();
    }
}
