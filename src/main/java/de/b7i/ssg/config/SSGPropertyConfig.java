package de.b7i.ssg.config;

import de.b7i.ssg.config.SSGConfig;

import java.util.Properties;

public class SSGPropertyConfig implements SSGConfig {

    private final Properties properties;

    public SSGPropertyConfig(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getOutDir() {
        return (String) properties.get("out.dir");
    }
}
