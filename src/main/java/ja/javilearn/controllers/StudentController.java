package ja.javilearn.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ja.javi.jdbcrefresh.models.Student;
import ja.javi.jdbcrefresh.models.database.DAO;
import ja.javi.jdbcrefresh.models.database.StudentDAOMySQL;

@Controller
public class StudentController {
	
	@GetMapping("/student/all")
	public ModelAndView getAll(ModelAndView mv) {
		DAO<Student, ?> dao = new StudentDAOMySQL();
		List<Student> students = dao.getAll();
		mv.addObject("studentList", students);
		mv.setViewName("students");
		return mv;
	}
}
