package ja.javilearn.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			return new ModelAndView("redirect:/error.html");
		}
		mv.addObject("studentList", students);
		mv.setViewName("students");
		return mv;
	}
	
	@GetMapping("/student/{ID}")
	public ModelAndView detailStudent(ModelAndView mv, @PathVariable(value="ID") Long id) {
		DAO<Student, Long> dao = new StudentDAOMySQL();
		Student student = dao.getById(id);
		if (student == null) {
			logger.severe("Student out of range");
			return new ModelAndView("redirect:/error.html");
		}
		mv.addObject("student", student);
		mv.setViewName("student_detail");
		return mv;
	}
}
