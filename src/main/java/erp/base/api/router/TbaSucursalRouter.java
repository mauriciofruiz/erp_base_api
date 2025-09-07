package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaSucursalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaSucursalRouter {

    @Bean
    public RouterFunction<ServerResponse> branchRouter(TbaSucursalHandler handler){
        return RouterFunctions.route()
                .POST(PathConstants.BRANCH, handler::create)
                .PUT(PathConstants.BRANCH + PathConstants.ID_PARAM, handler::update)
                .GET(PathConstants.BRANCH  + PathConstants.ID_PARAM, handler::getById)
                .GET(PathConstants.BRANCH , handler::getRequestBySearchParams)
                .build();
    }
}
