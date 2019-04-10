package ja.javi.jdbcrefresh.dbconnect;

import java.sql.Connection;

public interface IDBConnection {
	void close();

	void open();

	Connection getConnection();
	
	void commit();
	
	void rollback();
}
