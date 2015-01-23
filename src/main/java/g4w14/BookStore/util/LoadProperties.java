package g4w14.BookStore.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
	
	/**
	 * Was used for testing before JSF
	 * 
	 * Retrieve the properties file.
	 * 
	 * @param propertiesFileName
	 *            : Name of the properties file
	 * @throws IOException
	 *             : Error while reading the file
	 * @throws NullPointerException
	 *             : File not found
	 */
	public static String[] loadProperties() throws IOException,
			NullPointerException {

		Properties properties = new Properties();
		// Load the properties file into the Properties object
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("db.properties");
		properties.load(stream);

		String values[] = new String[3];
		// Load the properties into the variables for making db connections
		values[0] = properties.getProperty("url");
		values[1] = properties.getProperty("user");
		values[2] = properties.getProperty("password");
		return values;
	}

}
