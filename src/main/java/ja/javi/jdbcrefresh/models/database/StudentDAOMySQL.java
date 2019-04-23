package ja.javi.jdbcrefresh.models.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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

	
	private final String INSERT_QUERY = "INSERT INTO student (name, surname, birthdate) VALUES (?, ?, ?)";
	private final String GETONE_QUERY = "SELECT * FROM student WHERE id_student = ?";
	private final String GETALL_QUERY = "SELECT * FROM student";
	private final String DELETE_QUERY = "DELETE FROM student WHERE id_student = ?";
	public StudentDAOMySQL() {
		connection = new MySQLConnection();
	}

	@Override
	public Student getById(Long id) {
		connection.open();
		

		Student student = null;
		try {
			PreparedStatement stm = connection.getConnection().prepareStatement(GETONE_QUERY);
			stm.setLong(1, id);
			logger.info("Query: " + stm.toString());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				student = assembleFromResultSet(rs);
			}
		} catch (SQLException e) {
			logger.severe("Exception when selecting student of id: " + id);
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
		
		List<Student> students = new ArrayList<>();
		try {
			PreparedStatement stm = connection.getConnection().prepareStatement(GETALL_QUERY);
			logger.info("Query: " + stm.toString());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Student student = assembleFromResultSet(rs);
				if (student != null) {
					students.add(student);
				}
			}
		} catch (SQLException e) {
			logger.severe("Error retrieving student. Current amount of retrieved students is: " + students.size());
		} finally {
			connection.close();
		}
		if (debugMode) {
			logger.info("=== Students from database ===");
			students.forEach(student -> logger.info(student.toString()));
		}
		return students;
	}

	@Override
	public void delete(Student student) {
		connection.open();
		
		logger.info("Deleting student: " + student.toString());
		Long id = student.getId_student();
		if (id == null) {
			logger.severe("There was an error when retrieving the id of student: " + student.toString());
		}
		try {
			PreparedStatement stm = connection.getConnection().prepareStatement(DELETE_QUERY);
			stm.setLong(1, id);
			logger.info("Query: " + stm.toString());
			stm.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.severe("Exception when deleting Student: " + student.toString());
			connection.rollback();
		} finally {
			connection.close();
		}
		if (debugMode) {
			logger.info("Deleted student from database: " + student.toString());
		}
	}

	@Override
	public void insert(Student student) {
		connection.open();
		
		logger.info("Inserting student: " + student.toString());
		try {
			PreparedStatement stm = connection.getConnection().prepareStatement(INSERT_QUERY);
			stm.setString(1, student.getName());
			stm.setString(2, student.getSurname());
			stm.setDate(3, new java.sql.Date(student.getBirthdate().getTime()));
			logger.info("Query: " + stm.toString());
			stm.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.severe("Exception when inserting Student: " + student.toString());
			connection.rollback();
		} finally {
			connection.close();
		}
		if (debugMode) {
			logger.info("Inserted student into database: " + student.toString());
		}
	}

	@Override
	public void update(Student original, Student creating) {
		connection.open();
		Long id = original.getId_student();
		String name = creating.getName();
		String surname = creating.getSurname();
		String birthdate = creating.getBirthDate();

		if (id == null || name == null || surname == null || birthdate == null) {
			logger.severe(
					"ERROR retrieving fields when updating " + original.toString() + " to " + creating.toString());
		}

		String query = "UPDATE student SET name = ?, surname = ?, birthdate = ? WHERE id_student = ?";
		logger.info("Updating student of id: " + id + " to:" + creating.toString());
		try {
			PreparedStatement stm = connection.getConnection().prepareStatement(query);
			stm.setString(1, creating.getName());
			stm.setString(2, creating.getSurname());
			stm.setDate(3, new java.sql.Date(creating.getBirthdate().getTime()));
			stm.setLong(4, id);
			logger.info("Query: " + query);
			stm.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.severe("Exception when updating Student: " + creating.toString());
			connection.rollback();
		} finally {
			connection.close();
		}
		if (debugMode) {
			logger.info("Updated student of id: " + id + " to:" + creating.toString());
		}
	}

	/**
	 * TODO: think on extracting these kind of methods into a utility class Maybe
	 * with reflection where I could also not harcode the values in queries or maybe
	 * even programatically generate them
	 */
	public Student assembleFromResultSet(ResultSet rs) {
		Student creating = new Student();
		try {
			Long id_student = rs.getLong("id_student");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String birthDateString = rs.getString("birthdate");
			Date birthDate = creating.getFormat().parse(birthDateString);
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
