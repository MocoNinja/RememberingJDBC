package ja.javi.jdbcrefresh.dbconnect;

import java.sql.Connection;

public interface IDBConnection {
	void close();

	void commit();

	void open();

	void rollback();

	Connection getConnection();
}
