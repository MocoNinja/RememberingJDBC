package ja.javi.jdbcrefresh;


import ja.javi.jdbcrefresh.models.database.StudentDAOMySQL;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello, world!");
		StudentDAOMySQL sdao = new StudentDAOMySQL();
		sdao.getAll();
		sdao.getById(1L);
	}
}
