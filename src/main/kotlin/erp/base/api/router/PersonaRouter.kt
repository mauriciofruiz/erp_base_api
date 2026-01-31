package erp.base.api.router

import erp.base.api.constants.PathConstants
import erp.base.api.handler.PersonaHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class PersonaRouter {

    @Bean
    fun personRouter(handler: PersonaHandler): RouterFunction<ServerResponse> =
        RouterFunctions.route()
            .POST(PathConstants.PERSON, handler::create)
            .PUT(PathConstants.PERSON + PathConstants.ID_PARAM, handler::update)
            .GET(PathConstants.PERSON + PathConstants.ID_PARAM, handler::getById)
            .GET(PathConstants.PERSON + PathConstants.PROVIDER + PathConstants.ID_PARAM, handler::getPersonProvidersByProviderId)
            .GET(PathConstants.PROVIDER + PathConstants.PERSON, handler::getAllPersonProviders)
            .GET(PathConstants.PERSON + PathConstants.CLIENT + PathConstants.ID_PARAM, handler::getPersonClientByProviderId)
            .GET(PathConstants.CLIENT + PathConstants.PERSON + PathConstants.FICHA, handler::getAllPersonClients)
            .GET(PathConstants.PERSON, handler::getAllPeople)
            .build()
}
