package erp.base.api.security.jwt;

import erp.base.api.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clase que se encarga de autenticar el token
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtProvider jwtProvider;

    /**
     * Metodo que se encarga de autenticar el token
     * @param authentication es el token
     * @return el token autenticado
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> jwtProvider.getClaims(auth.getCredentials().toString()))
                .onErrorResume(e -> {
                    log.error("Error while authenticating: {}", e.getMessage());
                    return Mono.error(new CustomException(HttpStatus.UNAUTHORIZED, "bad token"));
                })
                .map(claims -> new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        Stream.of(claims.get("roles"))
                                .map(role -> (List<Map<String, String>>) role)
                                .flatMap(role -> role.stream()
                                        .map(r -> r.get("authority"))
                                        .map(SimpleGrantedAuthority::new))
                                .collect(Collectors.toList())
                ));
    }


}
