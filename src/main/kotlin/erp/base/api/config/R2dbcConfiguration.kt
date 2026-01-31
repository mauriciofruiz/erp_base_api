package erp.base.api.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.core.DatabaseClient
import java.net.URI

@Configuration
class R2dbcConfiguration {

    @Value("\${spring.r2dbc.url}")
    private lateinit var databaseUrl: String

    @Value("\${spring.r2dbc.username}")
    private lateinit var username: String

    @Value("\${spring.r2dbc.password}")
    private lateinit var password: String

    @Value("\${spring.r2dbc.schema:public}")
    private lateinit var schema: String

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val uri = URI.create(databaseUrl.replace("r2dbc:postgresql", "https"))
        val host = uri.host
        val port = uri.port
        val database = uri.path.removePrefix("/")

        return PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .host(host)
                .port(port)
                .database(database)
                .username(username)
                .password(password)
                .schema(schema)
                .build()
        )
    }

    @Bean
    fun databaseClient(connectionFactory: ConnectionFactory) =
        DatabaseClient.create(connectionFactory)
}
