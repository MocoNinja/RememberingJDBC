package ja.javi.jdbcrefresh.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MySQLConnection implements DBConnection {
	private static final MySQLConnection instance = new MySQLConnection();
	private final IDatabaseConfigurator configuration = PropertiesDatabaseConfiguration.getInstance();
	private final String connectionString;
	private Connection connection;

	Logger logger = Logger.getLogger(getClass().getName());

	private MySQLConnection() {
		this.connectionString = prepareConnectionString();
	}

	private String prepareConnectionString() {
		final String prefix = "jdbc:mysql://";
		String connectionString = String.format("%s%s:%s/%s", prefix, configuration.getHostname(),
				configuration.getPort(), configuration.getDatabase());
		StringBuilder msg = new StringBuilder("Created connection String: " + connectionString + ". Autocommit is: ");
		if (configuration.hasAutocommit()) {
			msg.append("on");
		} else {
			msg.append("off");
		}
		logger.info(msg.toString());
		return connectionString;
	}

	@Override
	public void open() {
		try {
			connection = DriverManager.getConnection(connectionString, configuration.getUser(),
					configuration.getPassword());
			connection.setAutoCommit(connection.getAutoCommit());
			logger.fine("Connection to database successfully stablished!");
		} catch (SQLException e) {
			logger.severe("Error creating connection to database");
		}
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
				logger.info("Connection to database successfully closed!");
			} catch (SQLException e) {
				logger.severe("Error closing connection to database");
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
