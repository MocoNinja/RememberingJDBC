package ja.javi.jdbcrefresh.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DBConnection {
	private static final MySQLConnection instance = new MySQLConnection();
	private final DatabaseConfiguration configuration = DatabaseConfiguration.getInstance();
	private final String connectionString;
	private Connection connection;

	private MySQLConnection() {
		this.connectionString = prepareConnectionString();
	}

	private String prepareConnectionString() {
		final String prefix = "jdbc:mysql://";
		String connectionString = String.format("%s%s:%s/%s", prefix, configuration.getHostname(),
				configuration.getPort(), configuration.getDatabase());
		return connectionString;
	}

	@Override
	public void open() {
		try {
			connection = DriverManager.getConnection(connectionString, configuration.getUser(),
					configuration.getPassword());
		} catch (SQLException e) {
			System.err.println("Error creating connection to database");
		}
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Error closing connection to database");
			}
		}
	}

	public static MySQLConnection getConnector() {
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

}
