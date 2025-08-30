package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	public static Properties properties;

	// Load properties file once
	private static void loadProperties() throws IOException {
		if (properties == null) {
			FileInputStream fis = new FileInputStream("config/config.properties");
			properties = new Properties();
			properties.load(fis);
		}
	}

	// Get value by Key
	public static String getProperty(String key) throws IOException {
		loadProperties();
		String value = properties.getProperty(key);
		if (value != null) {
			return value;
		} else {
			throw new RuntimeException("Property" + key + "not found in config.properties");
		}
	}
}
