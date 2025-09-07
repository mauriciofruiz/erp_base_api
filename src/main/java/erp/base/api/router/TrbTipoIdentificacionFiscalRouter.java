package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TrbTipoContribuyenteHandler;
import erp.base.api.handler.TrbTipoIdentificacionFiscalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TrbTipoIdentificacionFiscalRouter {

    /**
     * Método que maneja las rutas de tipo contribuyente.
     * @param trbTipoIdentificacionFiscalHandler handler
     * @return RouterFunction
     */
    @Bean
    public RouterFunction<ServerResponse> fiscalIdentificationTypeRouter(TrbTipoIdentificacionFiscalHandler trbTipoIdentificacionFiscalHandler){
        return RouterFunctions.route()
                .POST(PathConstants.FISCAL_IDENTIFICATION_TYPE, trbTipoIdentificacionFiscalHandler::createFiscalIdentificationType)
                .PUT(PathConstants.FISCAL_IDENTIFICATION_TYPE+PathConstants.ID_PARAM, trbTipoIdentificacionFiscalHandler::updateFiscalIdentificationType)
                .GET(PathConstants.FISCAL_IDENTIFICATION_TYPE + PathConstants.ID_PARAM, trbTipoIdentificacionFiscalHandler::getFiscalIdentificationTypeById)
                .GET(PathConstants.FISCAL_IDENTIFICATION_TYPE, trbTipoIdentificacionFiscalHandler::getAllFiscalIdentificationType)
                .build();
    }
}
