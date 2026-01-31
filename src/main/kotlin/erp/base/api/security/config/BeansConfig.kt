package erp.base.api.security.config

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BeansConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = object : PasswordEncoder {
        override fun encode(rawPassword: CharSequence): String = rawPassword.toString()
        override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean =
            encodedPassword == rawPassword.toString()
    }

    @Bean
    fun resources(): WebProperties.Resources = WebProperties.Resources()
}
