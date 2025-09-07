package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaRolUsuarioHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaRolUsuarioRouter {

    @Bean
    public RouterFunction<ServerResponse> userRoleRouter(TbaRolUsuarioHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.USER_ROLE, handler::create)
                .PUT(PathConstants.USER_ROLE + PathConstants.ID_PARAM, handler::update)
                .DELETE(PathConstants.USER_ROLE + PathConstants.ID_PARAM, handler::delete)
                .GET(PathConstants.USER_ROLE, handler::findAll)
                .GET(PathConstants.USER_ROLE + PathConstants.ID_PARAM, handler::findRolByUserId)
                .build();
    }
}
