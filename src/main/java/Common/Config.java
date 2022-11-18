package Common;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {
    private final String configFile;
    private final Map<String, Object> config;

    public Config(String configFile) {
        this.configFile = configFile;
        this.config = new HashMap<>();
    }

    public Map<String, Object> getConfig() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        InputStream in = Config.class.getClassLoader().getResourceAsStream(configFile);
        Properties properties = new Properties();
        properties.load(in);

        for (String key : properties.stringPropertyNames()) {
            String className = properties.getProperty(key);
            Object instance = Class.forName(className).getDeclaredConstructor().newInstance();
            config.put(key, instance);
        }
        return config;
    }
}