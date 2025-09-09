package erp.base.api.security.config;

import erp.base.api.constants.PathConstants;
import erp.base.api.repository.TbaUsuarioRepository;
import erp.base.api.security.jwt.JwtFilter;
import erp.base.api.security.repository.SecurityContextRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

/**
 * Configuración de seguridad
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
@Log4j2
public class MainSecurity {

    private final SecurityContextRepository securityContextRepository;
    private final TbaUsuarioRepository tbaUsuarioRepository;

    @Bean
    ReactiveAuditorAware<Integer> auditorAware() {
        return () -> ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .flatMap(e -> tbaUsuarioRepository.findByNickUsrIgnoreCase(e.toString()).map(
                        user -> user.getIdUsr()
                ));
    }

    /**
     * Configuración de seguridad
     *
     * @param http      es el objeto que se encarga de configurar la seguridad
     * @param jwtFilter es el filtro que se encarga de validar el token
     * @return
     */
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {
        http
                .cors(corsSpec -> corsSpec.configurationSource(corsConfigurationSource()))
                .authorizeExchange(authorizeExchangeSpec ->
                        authorizeExchangeSpec
                                .pathMatchers(PathConstants.LOGIN).permitAll()
                                // Swagger UI endpoints
                                .pathMatchers("/swagger-ui/**", "/swagger-ui.html").permitAll()
                                .pathMatchers("/v3/api-docs/**", "/v3/api-docs").permitAll()
                                .pathMatchers("/swagger-resources/**", "/webjars/**").permitAll()
                                .anyExchange().authenticated()
                )
                .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.CORS)
                .securityContextRepository(securityContextRepository)
                .formLogin(formLoginSpec -> formLoginSpec.disable())
                .logout(logoutSpec -> logoutSpec.disable())
                .csrf(csrfSpec -> csrfSpec.disable());
        return http.build();
    }

    /**
     * Configuración de CORS
     *
     * @return CorsConfigurationSource
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(Boolean.TRUE);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
