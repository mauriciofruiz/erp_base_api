package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.TipoIdentificacionFiscalHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class TipoIdentificacionFiscalRouter {

    @Bean
    fun fiscalIdentificationTypeRouter(handler: TipoIdentificacionFiscalHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.FISCAL_IDENTIFICATION_TYPE, handler::createFiscalIdentificationType)
            .PUT(PathConstants.FISCAL_IDENTIFICATION_TYPE + PathConstants.ID_PARAM, handler::updateFiscalIdentificationType)
            .GET(PathConstants.FISCAL_IDENTIFICATION_TYPE + PathConstants.ID_PARAM, handler::getFiscalIdentificationTypeById)
            .GET(PathConstants.FISCAL_IDENTIFICATION_TYPE, handler::getAllFiscalIdentificationType)
            .build()
}
