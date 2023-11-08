package golf.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesCache {
    private final Properties configProp = new Properties();
    private static final Logger LOG = LogManager.getLogger(PropertiesCache.class);

    private PropertiesCache() {
        //Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            configProp.load(in);
            System.out.println("\n----------Reading all properties from the file----------\n");
        } catch (IOException e) {
            System.out.println("Can't read properties");
            LOG.error("Can't read properties: " + e);
            throw new RuntimeException("Can't read properties", e);
        }
    }
    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }

    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }


}
