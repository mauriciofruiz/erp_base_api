package com.sicon.tba.security.router;

import com.sicon.tba.constants.PathConstants;
import com.sicon.tba.security.handler.LoginHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Clase que maneja las rutas de login
 */
@Configuration
@Slf4j
public class LoginRouter {

    /**
     * Metodo que maneja las rutas de login
     * @param handler handler
     * @return RouterFunction
     */
    @Bean
    RouterFunction<ServerResponse> routerLogin(LoginHandler handler) {
        return RouterFunctions.route()
                .POST(PathConstants.LOGIN, handler::login)
                .GET(PathConstants.LOGOUT, handler::logout)
                .GET(PathConstants.SESSION_STATE, handler::sessionState)
                .build();
    }
}
