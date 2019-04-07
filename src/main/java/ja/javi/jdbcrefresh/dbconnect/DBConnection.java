package ja.javi.jdbcrefresh.dbconnect;

public interface DBConnection {
	void close();

	void open();
}
