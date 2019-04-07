package ja.javi.jdbcrefresh.dbconnect;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseConfiguration {
	private final Map<String, String> credentials = new HashMap<>();
	private static final DatabaseConfiguration instance = new DatabaseConfiguration();

	private DatabaseConfiguration() {
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
		credentials.put("hostname", prop.getProperty("hostname"));
		credentials.put("port", prop.getProperty("port"));
		credentials.put("database", prop.getProperty("database"));
		credentials.put("username", prop.getProperty("username"));
		credentials.put("password", prop.getProperty("password"));
	}

	public void readCredentials() {
		for (String key : credentials.keySet()) {
			String msg = String.format("The key \"%s\" has a value of: \"%s\"", key, credentials.get(key));
			System.out.println(msg);
		}
	}

	public static DatabaseConfiguration getInstance() {
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
}