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

    public Map<String, Object> getConfig() {
        InputStream in = Config.class.getClassLoader().getResourceAsStream(configFile);
        Properties properties = new Properties();
        try {
            properties.load(in);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : properties.stringPropertyNames()) {
            try {
                String className = properties.getProperty(key);
                Object instance = Class.forName(className).getDeclaredConstructor().newInstance();
                config.put(key, instance);
            }
            catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                   IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return config;
    }
}