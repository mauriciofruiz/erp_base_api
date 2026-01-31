package erp.base.api.security.handler

import erp.base.api.security.dto.LoginDto
import erp.base.api.security.dto.LoginResponseDto
import erp.base.api.security.service.UserService
import erp.base.api.service.SesionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
@Tag(name = "Authentication", description = "Authentication endpoints")
class LoginHandler(
    private val userService: UserService,
    private val sesionService: SesionService,
) {

    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Login successful",
                content = [Content(schema = Schema(implementation = LoginResponseDto::class))]
            ),
            ApiResponse(responseCode = "401", description = "Invalid credentials"),
            ApiResponse(responseCode = "400", description = "Invalid request body"),
        ]
    )
    fun login(request: ServerRequest): Mono<ServerResponse> {
        val loginDtoMono = request.bodyToMono(LoginDto::class.java)
        return loginDtoMono.flatMap { dto ->
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.login(dto), LoginResponseDto::class.java)
        }
    }

    @Operation(summary = "User logout", description = "Logs out the current user and invalidates the JWT token")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Logout successful"),
            ApiResponse(responseCode = "401", description = "Unauthorized - Invalid token"),
        ]
    )
    fun logout(request: ServerRequest): Mono<ServerResponse> {
        val token = request.headers().header("Authorization")[0].replace("Bearer ", "")
        return userService.logout(token).flatMap { message ->
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message)
        }
    }

    fun sessionState(request: ServerRequest): Mono<ServerResponse> {
        val token = request.headers().header("Authorization")[0].replace("Bearer ", "")
        return sesionService.getSessionByToken(token).flatMap { message ->
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message.estado)
        }
    }
}
