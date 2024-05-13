package de.b7i.ssg.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SSGConfigReader {

    public SSGConfig read(String path) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(path));
        return new SSGPropertyConfig(props);
    }

}
