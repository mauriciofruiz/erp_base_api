package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.RolHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RolRouter {

    @Bean
    public RouterFunction<ServerResponse> rolRouterFunction(RolHandler rolHandler) {
        return RouterFunctions.route()
                .GET(PathConstants.ROLE + PathConstants.ID_PARAM, rolHandler::getById)
                //.GET(PathConstants.ROLE, rolHandler::getAllActives)
                .build();
    }
}
