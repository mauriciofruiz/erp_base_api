package com.sicon.tba.security.handler;

import com.sicon.tba.security.dto.LoginDto;
import com.sicon.tba.security.dto.LoginResponseDto;
import com.sicon.tba.security.service.UserService;
import com.sicon.tba.service.SesionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Clase que maneja las peticiones de login
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginHandler {

    private final UserService userService;
    private final SesionService sesionService;

    /**
     * Metodo que maneja la peticion de login
     * @param request request
     * @return ServerResponse
     */
    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginDto> loginDtoMono = request.bodyToMono(LoginDto.class);
        return loginDtoMono
                .flatMap(dto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.login(dto), LoginResponseDto.class));
    }

    /**
     * Método que maneja la petición de logout
     * @param request la solicitud de logout
     * @return una respuesta de logout exitoso
     */
    public Mono<ServerResponse> logout(ServerRequest request) {
        String token = request.headers().header("Authorization").get(0).replace("Bearer ", "");
        return userService.logout(token).flatMap(message -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message));
    }

    public Mono<ServerResponse> sessionState(ServerRequest request) {
        String token = request.headers().header("Authorization").get(0).replace("Bearer ", "");
        return sesionService.getSessionByToken(token).flatMap(message -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message.getEstado()));
    }
}
