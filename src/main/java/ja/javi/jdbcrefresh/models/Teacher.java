package ja.javi.jdbcrefresh.models;

public class Teacher {
	private Long id_teacher;
	private String name;
	private String surname;

	public Teacher() {

	}

	public Teacher(Long id_student, String name, String surname) {
		this.id_teacher = id_student;
		this.name = name;
		this.surname = surname;
	}

	public Long getId_student() {
		return id_teacher;
	}

	public void setId_student(Long id_student) {
		this.id_teacher = id_student;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Teacher [id_teacher=");
		builder.append(id_teacher);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_teacher == null) ? 0 : id_teacher.hashCode());
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
		Teacher other = (Teacher) obj;
		if (id_teacher == null) {
			if (other.id_teacher != null)
				return false;
		} else if (!id_teacher.equals(other.id_teacher))
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
