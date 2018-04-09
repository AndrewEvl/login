package by.login.controller;

import by.login.entity.Status;
import by.login.entity.User;
import by.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Random;

@Controller
/*
Controller application.
 */
public class UserController {

    private UserRepository userRepository;
    private static Long ID;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @ModelAttribute("id")
    public Long id() {
        return null;
    }

    @GetMapping("/")
    public String saveUserGet() {
        return "save-user-page";
    }

    /*
    Method to upload avatar user on server.
     */
    @PostMapping("/")
    public String saveUserPost(User user, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File fileSave = new File(file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileSave + ".jpeg"));
                stream.write(bytes);
                stream.close();
                //Here you can specify the full path to the file
                String absolutePath = fileSave.getPath();
                user.setImage(absolutePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stub();
        user.setStatusNow(Status.ONLINE);
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/find-user")
    public String findUserByIdGet() {
        return "find-user";
    }

    @PostMapping("/find-user")
    public String findUserByIdPost(User user, Model model) {
        stub();
        User userById = userRepository.findById(user.getId()).get();
        ID = user.getId();
        model.addAttribute("user", userById);
        return "redirect:/user-info";
    }

    /*
    I do not know, but on the normal ID is not passed to the method
     */
    @GetMapping("/user-info")
    public String userInfoGet(Model model) {
        User user = userRepository.findById(ID).get();
        Status statusNow = user.getStatusNow();
        user.setStatusNow(Status.values()[new Random()
                .nextInt(Status.values().length)]);
        user.setPreviousStatus(statusNow);
        userRepository.save(user);
        stub();
        model.addAttribute("user", user);
        model.addAttribute("id", ID);
        model.addAttribute("image", "/" + user.getImage());
        return "user-info";
    }

    @GetMapping("/user-info{id}")
    public String userInfoIdGet(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "user-info";
    }

    //Stub for 3 seconds.
    private void stub (){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
