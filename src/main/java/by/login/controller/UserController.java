package by.login.controller;

import by.login.entity.Status;
import by.login.entity.User;
import by.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserRepository userRepository;
    private Long ID;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @ModelAttribute("id")
    public Long id(){
        return null;
    }

    @GetMapping("/")
    public String saveUserGet (){
        return "save-user-page";
    }

    @PostMapping("/")
    public String saveUserPost (User user){
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/find-user")
    public String findUserByIdGet (){
        return "find-user";
    }

    @PostMapping("/find-user")
    public String findUserByIdPost(User user, Model model){
        User userById = userRepository.findById(user.getId()).get();
        ID = user.getId();
//        model.addAttribute("id",userById.getId());
        return "redirect:/user-info";
    }

    @GetMapping("/user-info")
    public String userInfoGet (Model model){
        model.addAttribute("id",ID);
        return "redirect:/user-info/{id}";
    }

    @GetMapping("/user-info/{id}")
    public String userInfoIdGet (@PathVariable ("id") Long id,Model model){
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "user-info";
    }
}
