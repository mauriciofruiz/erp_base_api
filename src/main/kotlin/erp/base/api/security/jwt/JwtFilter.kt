package erp.base.api.security.jwt

import erp.base.api.exception.CustomException
import erp.base.api.service.SesionService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class JwtFilter(
    private val sesionService: SesionService,
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request: ServerHttpRequest = exchange.request
        val path = request.path.value()

        if (path.contains("login") ||
            path.startsWith("/swagger-ui") ||
            path.startsWith("/v3/api-docs") ||
            path.startsWith("/swagger-resources") ||
            path.startsWith("/webjars")
        ) {
            return chain.filter(exchange)
        }

        val auth = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
            ?: return Mono.error(CustomException(HttpStatus.BAD_REQUEST, "no token was found"))
        if (!auth.startsWith("Bearer "))
            return Mono.error(CustomException(HttpStatus.BAD_REQUEST, "invalid auth"))
        val token = auth.replace("Bearer ", "")
        exchange.attributes["token"] = token

        return sesionService.getSessionByToken(token)
            .flatMap { sesion ->
                val isActive = sesion.estado
                if (isActive != true) {
                    Mono.error<Void>(CustomException(HttpStatus.UNAUTHORIZED, "Token is not active"))
                } else {
                    chain.filter(exchange)
                }
            }
    }
}
