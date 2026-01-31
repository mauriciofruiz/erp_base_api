package erp.base.api.security.config

import erp.base.api.constants.PathConstants
import erp.base.api.repository.UsuarioRepository
import erp.base.api.security.jwt.JwtFilter
import erp.base.api.security.repository.SecurityContextRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class MainSecurity(
    private val securityContextRepository: SecurityContextRepository,
    private val usuarioRepository: UsuarioRepository,
) {

    @Bean
    fun auditorAware(): ReactiveAuditorAware<Int> =
        ReactiveAuditorAware {
            ReactiveSecurityContextHolder.getContext()
                .map { ctx: SecurityContext -> ctx.authentication }
                .filter { obj: Authentication -> obj.isAuthenticated }
                .map { obj: Authentication -> obj.principal }
                .flatMap { e ->
                    usuarioRepository.findByNickUsrIgnoreCase(e.toString()).map { user -> user.idUsr!! }
                }
        }

    @Bean
    fun filterChain(http: ServerHttpSecurity, jwtFilter: JwtFilter): SecurityWebFilterChain =
        http
            .cors { corsSpec -> corsSpec.configurationSource(corsConfigurationSource()) }
            .authorizeExchange { authorizeExchangeSpec ->
                authorizeExchangeSpec
                    .pathMatchers(PathConstants.LOGIN).permitAll()
                    .pathMatchers("/swagger-ui/**", "/swagger-ui.html").permitAll()
                    .pathMatchers("/v3/api-docs/**", "/v3/api-docs").permitAll()
                    .pathMatchers("/swagger-resources/**", "/webjars/**").permitAll()
                    .anyExchange().authenticated()
            }
            .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.CORS)
            .securityContextRepository(securityContextRepository)
            .formLogin { formLoginSpec -> formLoginSpec.disable() }
            .logout { logoutSpec -> logoutSpec.disable() }
            .csrf { csrfSpec -> csrfSpec.disable() }
            .build()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedOriginPatterns = listOf("*")
            allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE")
            allowedHeaders = listOf("*")
            exposedHeaders = listOf("*")
            allowCredentials = true
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
