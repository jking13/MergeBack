package com.jking.mergeback.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * Created by john on 4/1/17.
 */
public class PropertiesConfig {
    private final Configuration config;

    public PropertiesConfig() {
        try {
            this.config = new Configurations().properties("application.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public Configuration config(){
        return config;
    }
}
