package ja.javi.jdbcrefresh.dbconnect;

public interface IDBConnection {
	void close();

	void open();
}
