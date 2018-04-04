package by.login.controller;

import by.login.entity.User;
import by.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @GetMapping("/")
    public String testPageGet (){
        return "loginPage";
    }
}
