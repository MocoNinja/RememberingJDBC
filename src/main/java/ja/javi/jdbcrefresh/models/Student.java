package ja.javi.jdbcrefresh.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class Student {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(getClass().getName());

	private Long id_student;
	private String name;
	private String surname;
	private Date birthdate;

	public Student() {

	}

	public Long getId_student() {
		return id_student;
	}

	public void setId_student(Long id_student) {
		this.id_student = id_student;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public String getBirthDate() {
		return format.format(birthdate);
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setBirthDate(String string) {
		try {
			this.birthdate = format.parse(string);
		} catch (ParseException e) {
			logger.severe("Error parsing date: " + string);
		}
	}

	public SimpleDateFormat getFormat() {
		return format;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id_student=");
		builder.append(id_student);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", birthdate=");
		builder.append(birthdate);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((id_student == null) ? 0 : id_student.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (id_student == null) {
			if (other.id_student != null)
				return false;
		} else if (!id_student.equals(other.id_student))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
