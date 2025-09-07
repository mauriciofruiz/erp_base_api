package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.AccesoDocumentoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AccesoDocumentoRouter {

    @Bean
    public RouterFunction<ServerResponse> accesoDocumentoRoutes(AccesoDocumentoHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.DOCUMENT_ACCESS, handler::create)
                .PUT(PathConstants.DOCUMENT_ACCESS + PathConstants.FIND, handler::findByDocumentIdAndDocumentTypeIdAndName)
                .PUT(PathConstants.DOCUMENT_ACCESS + PathConstants.GET + PathConstants.FIND, handler::findByDocumentIdAndDocumentTypeId)
                .PUT(PathConstants.DOCUMENT_ACCESS + PathConstants.DELETE, handler::deleteByDocumentIdAndDocumentTypeId)
                .GET(PathConstants.DOCUMENT_ACCESS + PathConstants.GET, handler::getAll)
                .build();
    }
}
