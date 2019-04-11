package ja.javi.jdbcrefresh.models.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import ja.javi.jdbcrefresh.dbconnect.IDBConnection;
import ja.javi.jdbcrefresh.dbconnect.MySQLConnection;
import ja.javi.jdbcrefresh.models.Student;

public class StudentDAOMySQL implements DAO<Student, Long> {
	private final boolean debugMode = true; // This could be another parameter to read from configuration
	private IDBConnection connection;
	Logger logger = Logger.getLogger(getClass().getName());

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
			logger.info("Query: " + query);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				student = assembleFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		if (debugMode && student != null) {
			logger.info("Student from database: " + student.toString());
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
			logger.info("Query: " + query);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Student student = assembleFromResultSet(rs);
				if (student != null) {
					students.add(student);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		if (debugMode) {
			logger.info("=== Students from database ===");
			students.forEach(student -> logger.info(student.toString()));
		}
		return students;
	}

	
	@Override
	public void delete(Student t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Student t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Student t) {
		// TODO Auto-generated method stub
		
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
			if (debugMode) {
				logger.info("Student: " + creating.toString() + " could successfully be assembled from database...");
			}
		} catch (SQLException e) {
			logger.severe("Error assembling user from result set");
			creating = null;
		} catch (ParseException e) {
			logger.severe("Error creating date...");
			creating = null;
		}
		return creating;
	}

}
