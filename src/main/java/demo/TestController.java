package demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("key", "value");
		model.addAttribute("key1", "value1");
		return "index";
	}
}
