package demo.controller;

import demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by PRASENJIT on 8/4/2015.
 */
@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
}
