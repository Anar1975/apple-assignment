package utils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesResolver {

    private static final String BASE_PROPERTIES_FILENAME = "automation.properties";
    private static final String LOCAL_PROPERTIES_FILENAME = "local.properties";
    private final Properties testProperties = resolveProperties();

    public Properties resolveProperties() {
        String systemProfileName = getSystemProfileName();
        Properties baseProperties = null;
        try {
            baseProperties = getBaseProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (systemProfileDoesNotExist(systemProfileName)) {
            try {
                assert baseProperties != null;
                baseProperties.putAll(getPropertiesFromFile(LOCAL_PROPERTIES_FILENAME));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                assert baseProperties != null;
                baseProperties.putAll(getProfileProperties(systemProfileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        baseProperties.putAll(System.getenv());
        baseProperties.putAll(System.getProperties());

        return baseProperties;
    }

    private Properties getPropertiesFromFile(String filename) throws IOException {
        Properties p = new Properties();
        p.load(getResourceAsStream(filename));
        return p;
    }

    private InputStream getResourceAsStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

    private boolean systemProfileDoesNotExist(String systemProfileName) {
        return StringUtils.isBlank(systemProfileName);
    }

    private String getSystemProfileName() {
        return System.getProperty("profile");
    }

    private Properties getBaseProperties() throws IOException {
        return getPropertiesFromFile(BASE_PROPERTIES_FILENAME);
    }

    private Properties getProfileProperties(String profileName) throws IOException {
        return getPropertiesFromFile(profileName + ".properties");
    }

    public String getProperty(String key) {
        return testProperties.getProperty(key);
    }
}
