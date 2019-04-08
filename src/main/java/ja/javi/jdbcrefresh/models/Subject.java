package ja.javi.jdbcrefresh.models;

public class Subject {
	private Long id_subject;
	private String name;
	private Teacher teacher;

	public Subject() {

	}

	public Subject(Long id_subject, String name, Teacher teacher) {
		super();
		this.id_subject = id_subject;
		this.name = name;
		this.teacher = teacher;
	}

	public Long getId_subject() {
		return id_subject;
	}

	public void setId_subject(Long id_subject) {
		this.id_subject = id_subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subject [id_subject=");
		builder.append(id_subject);
		builder.append(", name=");
		builder.append(name);
		builder.append(", teacher=");
		builder.append(teacher.toString());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_subject == null) ? 0 : id_subject.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
		Subject other = (Subject) obj;
		if (id_subject == null) {
			if (other.id_subject != null)
				return false;
		} else if (!id_subject.equals(other.id_subject))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}

}
