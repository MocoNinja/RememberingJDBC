package ja.javilearn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@GetMapping("/")
	public String loadHome() {
		return "home";
	}
	
	@GetMapping("/error")
	public String loadError() {
		return "error";
	}
}
