package erp.base.api

import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.Properties

@Component("preference")
class Preference(private val environment: Environment) : Serializable {

    private val properties = Properties()
    private val log = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    private fun loadProperties() {
        val activeProfile = environment.getProperty("spring.profiles.active")
        if ("consolidado" == activeProfile) {
            log.info("*********Consolidado**********")
            loadPropertiesFromResource("/services-consolidado.properties")
        } else {
            log.info("*********Desarrollo**********")
            loadPropertiesFromResource("/services-desarrollo.properties")
        }
    }

    private fun loadPropertiesFromResource(resourcePath: String) {
        try {
            javaClass.getResourceAsStream(resourcePath)?.use { input ->
                properties.load(input)
            } ?: log.error("Could not load properties file: {}", resourcePath)
        } catch (e: Exception) {
            log.error("Error loading properties file: {}", e.message)
        }
    }

    private fun getProperty(key: String, defaultValue: String?): String? =
        properties.getProperty(key, defaultValue)

    fun getAdquisicionesService(): String? = getProperty("adquisicionServiceUrl", null)
    fun getErpBaseApiService(): String? = getProperty("erpBaseApiServiceUrl", null)
    fun getInventarioService(): String? = getProperty("inventarioServiceUrl", null)
    fun getContabilidadService(): String? = getProperty("contabilidadServiceUrl", null)
    fun getVentasService(): String? = getProperty("ventasServiceUrl", null)
}
