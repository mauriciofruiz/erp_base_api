package erp.base.api.security.jwt;

import erp.base.api.exception.CustomException;
import erp.base.api.service.SesionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Esta clase se encarga de filtrar las peticiones que no sean de login y que contengan un token válido
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements WebFilter {

    private final SesionService sesionService;

    /**
     * Metodo que se encarga de filtrar las peticiones que no sean de login y que contengan un token válido
     * @param exchange sirve para obtener el token de la cabecera de la peticion
     * @param chain es la cadena de filtros que se deben ejecutar
     * @return Mono<Void>
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();
        
        // Skip JWT validation for login and Swagger UI endpoints
        if(path.contains("login") || 
           path.startsWith("/swagger-ui") || 
           path.startsWith("/v3/api-docs") || 
           path.startsWith("/swagger-resources") || 
           path.startsWith("/webjars")) {
            return chain.filter(exchange);
        }
        
        String auth = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(auth == null)
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "no token was found"));
        if(!auth.startsWith("Bearer "))
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "invalid auth"));
        String token = auth.replace("Bearer ", "");
        exchange.getAttributes().put("token", token);
        // Consultar la tabla de sesión para verificar si el token está activo
        return sesionService.getSessionByToken(token)
                .flatMap(sesion -> {
                    Boolean isActive = sesion.getEstado();
                    if (!isActive) {
                        return Mono.error(new CustomException(HttpStatus.UNAUTHORIZED, "Token is not active"));
                    } else {
                        return chain.filter(exchange);
                    }
                });

    }


}
