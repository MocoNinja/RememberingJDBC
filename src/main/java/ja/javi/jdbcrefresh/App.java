package ja.javi.jdbcrefresh;

import ja.javi.jdbcrefresh.dbconnect.MySQLConnection;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello, world!");
		MySQLConnection conn = new MySQLConnection();
		conn.open();
	}
}
