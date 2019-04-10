package ja.javi.jdbcrefresh.models.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ja.javi.jdbcrefresh.dbconnect.IDBConnection;
import ja.javi.jdbcrefresh.dbconnect.MySQLConnection;
import ja.javi.jdbcrefresh.models.Student;

public class StudentDAOMySQL implements DAO<Student, Long> {
	private IDBConnection connection;

	public StudentDAOMySQL() {
		connection = new MySQLConnection();
	}

	@Override
	public Student getById(Long id) {
		connection.open();
		String query = "SELECT * FROM student WHERE id_student = ?";
		Student student = null;
		try {
			PreparedStatement stm = connection.getConnection().prepareStatement(query);
			stm.setLong(1, id);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				student = assembleFromResultSet(rs);
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return student;
	}

	@Override
	public List<Student> getAll() {
		connection.open();
		String query = "SELECT * FROM student";
		List<Student> students = new ArrayList<>();
		try {
			PreparedStatement stm = connection.getConnection().prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Student student = assembleFromResultSet(rs);
				students.add(student);
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return students;
	}

	/**
	 * TODO: think on extracting these kind of methods into a utility class Maybe
	 * with reflection where I could also not harcode the values in queries or maybe
	 * even programatically generate them
	 */
	public Student assembleFromResultSet(ResultSet rs) {
		Student creating = new Student();
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		try {
			Long id_student = rs.getLong("id_student");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String birthDateString = rs.getString("birthdate");
			Date birthDate = df.parse(birthDateString);
			creating.setName(name);
			creating.setSurname(surname);
			creating.setId_student(id_student);
			creating.setBirthdate(birthDate);
		} catch (SQLException e) {
			System.err.println("Error assembling user from result set");
			creating = null;
		} catch (ParseException e) {
			System.err.println("Error creating date...");
			creating = null;
		}
		return creating;
	}
}
