package erp.base.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@SpringBootApplication
@EnableR2dbcRepositories
@EnableR2dbcAuditing
public class ErpBaseApi {
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.configure()
                .directory("./")
                .filename(".env")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
        
        // Set system properties from .env file
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
        
        SpringApplication.run(ErpBaseApi.class, args);
    }
}
