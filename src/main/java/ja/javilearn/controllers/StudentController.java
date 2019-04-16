package ja.javilearn.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ja.javi.jdbcrefresh.models.Student;
import ja.javi.jdbcrefresh.models.database.DAO;
import ja.javi.jdbcrefresh.models.database.StudentDAOMySQL;

@Controller
public class StudentController {

	Logger logger = Logger.getLogger(getClass().getName());

	@GetMapping("/student/all")
	public ModelAndView getAll(ModelAndView mv) {
		DAO<Student, ?> dao = new StudentDAOMySQL();
		List<Student> students = dao.getAll();
		if (students == null) {
			logger.severe("Students could not be retrieved from database");
			return new ModelAndView("redirect:/error");
		}
		mv.addObject("studentList", students);
		mv.setViewName("students");
		return mv;
	}

	@GetMapping("/student/{ID}")
	public ModelAndView detailStudent(ModelAndView mv, @PathVariable(value = "ID") Long id) {
		DAO<Student, Long> dao = new StudentDAOMySQL();
		Student student = dao.getById(id);
		if (student == null) {
			logger.severe("Student out of range");
			return new ModelAndView("redirect:/error");
		}
		mv.addObject("student", student);
		mv.setViewName("student_detail");
		return mv;
	}

	@GetMapping("/student/register")
	public ModelAndView insertStudentView(ModelAndView mv) {
		mv.setViewName("student_create");
		return mv;
	}

	@PostMapping("/student/register")
	public String insertStudent(Student student) {
		if (student != null) {
			System.out.println(student.toString());
			DAO<Student, Long> dao = new StudentDAOMySQL();
			dao.insert(student);
		} else {
			logger.severe("STUDENT IS NULL");
		}
		return "redirect:/";
	}
	
	@GetMapping("/student/remove/{ID}")
	public ModelAndView prepareStudentDelete(ModelAndView mv, @PathVariable(value = "ID") Long id) {
		DAO<Student, Long> dao = new StudentDAOMySQL();
		Student student = dao.getById(id);
		if (student == null) {
			logger.severe("Student out of range");
			return new ModelAndView("redirect:/error");
		}
		mv.addObject("student", student);
		mv.setViewName("student_remove");
		return mv;
	}
	
	/**
	 *
	 * TODO:
	 *  I can reproduce a bug where the students that were added by the mysql init script cannot be remove
	 *  Investigate the cause (I think it is related to MySQL)
	 */
	@PostMapping("/student/remove/")
	public String studentDelete(@ModelAttribute Student student) {
		if (student != null) {
			System.out.println(student.toString());
			DAO<Student, Long> dao = new StudentDAOMySQL();
			dao.delete(student);
		} else {
			logger.severe("STUDENT IS NULL");
		}
		return "redirect:/";
	}
	
	@GetMapping("/student/update/{ID}")
	public ModelAndView prepareStudentUpdate(ModelAndView mv, @PathVariable(value = "ID") Long id) {
		DAO<Student, Long> dao = new StudentDAOMySQL();
		Student student = dao.getById(id);
		if (student == null) {
			logger.severe("Student out of range");
			return new ModelAndView("redirect:/error");
		}
		mv.addObject("student", student);
		mv.setViewName("student_update");
		return mv;
	}
	
	@PostMapping("/student/update/")
	public String studentUpdate(@ModelAttribute Student student) {
		if (student != null) {
			System.out.println(student.toString());
			DAO<Student, Long> dao = new StudentDAOMySQL();
			Student old = dao.getById(student.getId_student());
			dao.update(old, student);
		} else {
			logger.severe("STUDENT IS NULL");
		}
		return "redirect:/";
	}
}
