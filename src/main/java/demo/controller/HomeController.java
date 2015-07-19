package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/")
	public String index(Model model){
		return "redirect:home";
	}
}
