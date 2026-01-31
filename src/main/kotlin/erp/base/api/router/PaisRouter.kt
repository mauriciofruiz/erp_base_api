package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.PaisHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class PaisRouter {

    @Bean
    fun countryRouter(handler: PaisHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.COUNTRY, handler::createCountry)
            .PUT(PathConstants.COUNTRY + PathConstants.ID_PARAM, handler::updateCountry)
            .GET(PathConstants.COUNTRY + PathConstants.ID_PARAM, handler::getCountryById)
            .GET(PathConstants.COUNTRY, handler::getAllCountries)
            .build()
}
