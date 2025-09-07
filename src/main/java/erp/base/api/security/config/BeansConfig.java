package erp.base.api.security.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfig {

    /**
     * Bean para encriptar las contraseñas
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                // No se utiliza en este caso, ya que las contraseñas ya están encriptadas
                return rawPassword.toString();
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                // Verificar la igualdad entre el password encriptado y el password en la base de datos
                return encodedPassword.equals(rawPassword.toString());
            }
        };
    }

    /**
     * Bean para configurar los recursos
     * @return WebProperties.Resources
     */
    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }
}
