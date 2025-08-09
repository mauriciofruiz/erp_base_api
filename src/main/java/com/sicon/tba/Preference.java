package com.sicon.tba;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

@Log4j2
@Component("preference")
public class Preference implements Serializable {
    private final Properties properties = new Properties();

    private final Environment environment;

    @Autowired
    public Preference(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    private void loadProperties() {
        String activeProfile = environment.getProperty("spring.profiles.active");

        if ("consolidado".equals(activeProfile)) {
            log.info("*********Consolidado**********");
            loadPropertiesFromResource("/services-consolidado.properties");
        } else {
            log.info("*********Desarrollo**********");
            loadPropertiesFromResource("/services-desarrollo.properties");
        }
    }

    private void loadPropertiesFromResource(String resourcePath) {
        try (InputStream input = getClass().getResourceAsStream(resourcePath)) {
            if (input != null) {
                properties.load(input);
            } else {
                log.error("Could not load properties file: {}", resourcePath);
            }
        } catch (IOException e) {
            log.error("Error loading properties file: {}", e.getMessage());
        }
    }

    public String getAdquisicionesService() {
        return getProperty("adquisicionServiceUrl", null);
    }

    public String getTbaService() {
        return getProperty("tbaServiceUrl", null);
    }

    private String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getInventarioService() {
        return getProperty("inventarioServiceUrl", null);
    }

    public String getContabilidadService() {
        return getProperty("contabilidadServiceUrl", null);
    }

    public String getVentasService() {
        return getProperty("ventasServiceUrl", null);
    }

}
