package ja.javi.jdbcrefresh.dbconnect;

import java.util.Map;

public interface IDatabaseConfigurator {
	public Map<String, String> getCredentials();

	public String getHostname();

	public String getPort();

	public String getDatabase();

	public String getUser();

	public String getPassword();

	public boolean hasAutocommit();

	public void readCredentials();

	public static IDatabaseConfigurator getInstance() {
		return null;
	}
}