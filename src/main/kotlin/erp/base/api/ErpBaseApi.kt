package erp.base.api

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
@EnableR2dbcAuditing
class ErpBaseApi

fun main(args: Array<String>) {
    val dotenv = Dotenv.configure()
        .directory("./")
        .filename(".env")
        .ignoreIfMalformed()
        .ignoreIfMissing()
        .load()
    dotenv.entries().forEach { System.setProperty(it.key, it.value) }
    runApplication<ErpBaseApi>(*args)
}
