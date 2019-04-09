package ja.javi.jdbcrefresh.dbconnect;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesDatabaseConfiguration implements IDatabaseConfigurator {
	private final Map<String, String> credentials = new HashMap<>();
	private static final PropertiesDatabaseConfiguration instance = new PropertiesDatabaseConfiguration();

	private PropertiesDatabaseConfiguration() {
		init();
	}

	private void init() {

		Properties prop = new Properties();
		String propFileName = "db-connection.properties";
		String path = "";
		String file = path + propFileName;
		InputStream inputStream;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(file);
			if (inputStream == null) {
				throw new IOException("File could not be found!");
			}
			prop.load(inputStream);
			readConfig(prop);
			inputStream.close();
		} catch (IOException e) {
			System.err.println("Error streaming file");
		}
	}

	private void readConfig(Properties prop) {
		for (String key : prop.stringPropertyNames()) {
			credentials.put(key, prop.getProperty(key));
		}
	}

	public void readCredentials() {
		for (String key : credentials.keySet()) {
			String msg = String.format("The key \"%s\" has a value of: \"%s\"", key, credentials.get(key));
			System.out.println(msg);
		}
	}

	public static PropertiesDatabaseConfiguration getInstance() {
		return instance;
	}

	public Map<String, String> getCredentials() {
		return credentials;
	}

	public String getHostname() {
		return credentials.get("hostname");
	}

	public String getPort() {
		return credentials.get("port");
	}

	public String getDatabase() {
		return credentials.get("database");
	}

	public String getUser() {
		return credentials.get("username");
	}

	public String getPassword() {
		return credentials.get("password");
	}

	public boolean hasAutocommit() {
		return credentials.get("autocommit").trim().toLowerCase().equals("true");
	}
}