package utils;

public class PropertiesHelper {

    PropertiesResolver propertiesResolver = new PropertiesResolver();

    public String getProperty(String property) {
        return propertiesResolver.getProperty(property);
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

}
