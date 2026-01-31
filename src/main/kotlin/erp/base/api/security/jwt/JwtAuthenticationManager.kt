package erp.base.api.security.jwt

import erp.base.api.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import org.slf4j.LoggerFactory

@Component
class JwtAuthenticationManager(
    private val jwtProvider: JwtProvider,
) : ReactiveAuthenticationManager {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun authenticate(authentication: Authentication): Mono<Authentication> =
        Mono.just(authentication)
            .map { auth -> jwtProvider.getClaims(auth.credentials.toString()) }
            .onErrorResume { e ->
                log.error("Error while authenticating: {}", e.message)
                Mono.error(CustomException(HttpStatus.UNAUTHORIZED, "bad token"))
            }
            .map { claims ->
                val rolesList = claims["roles"] as? List<*>
                val authorities = rolesList?.mapNotNull { elem ->
                    (elem as? Map<*, *>)?.get("authority")?.toString()?.let { SimpleGrantedAuthority(it) }
                } ?: emptyList()
                UsernamePasswordAuthenticationToken(claims.subject, null, authorities)
            }
}
