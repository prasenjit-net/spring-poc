package demo.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class LoginController {

	@RequestMapping("login")
	public String login(Model model) {
		log.entry(model);
		
		return log.exit("login");
	}

}
