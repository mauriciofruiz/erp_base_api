package erp.base.api.security.handler;

import erp.base.api.security.dto.LoginDto;
import erp.base.api.security.dto.LoginResponseDto;
import erp.base.api.security.service.UserService;
import erp.base.api.service.SesionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication", description = "Authentication endpoints")
public class LoginHandler {

    private final UserService userService;
    private final SesionService sesionService;

    /**
     * Metodo que maneja la peticion de login
     * @param request request
     * @return ServerResponse
     */
    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful", 
                    content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
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
    @Operation(summary = "User logout", description = "Logs out the current user and invalidates the JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout successful"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token")
    })
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
