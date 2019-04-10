package ja.javi.jdbcrefresh;

import java.util.List;

import ja.javi.jdbcrefresh.models.Student;
import ja.javi.jdbcrefresh.models.database.StudentDAOMySQL;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello, world!");
		StudentDAOMySQL sdao = new StudentDAOMySQL();
		List<Student> sts = sdao.getAll();
		sts.forEach(student -> System.out.println(student.toString()));
		Student s2 = sdao.getById(1L);
		System.out.println(s2.toString());
	}
}
