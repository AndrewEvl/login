package by.login.controller;

import by.login.entity.Status;
import by.login.entity.User;
import by.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

//    @ModelAttribute("id")
//    public Long id() {
//        return null;
//    }

    @Value("${error.massage}")
    private String errorMassage;

    @Value("${error.id}")
    private String errorId;

    @GetMapping("/")
    public String saveUserGet() {
        return "save-user-page";
    }

    /*
    Method to upload avatar user on server.
     */
    @PostMapping("/")
    public String saveUserPost(User user, Model model, @RequestParam("file") MultipartFile file) {
        if (user.getFirstName() != null && user.getFirstName().length() > 0
                && user.getLastName() != null && user.getLastName().length() > 0
                && user.getMail() != null && user.getMail().length() > 0
                && user.getPassword() != null && user.getPassword().length() > 0) {
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
            model.addAttribute("id", "Your unique ID : " +  user.getId());
            return "save-user-page";
        }
        model.addAttribute("errorMassage", errorMassage);
        return "save-user-page";
    }

    @GetMapping("/find-user")
    public String findUserByIdGet() {
        return "find-user";
    }

    @PostMapping("/find-user")
    public String findUserByIdPost(User user, Model model) {
        try {
            User userById = userRepository.findById(user.getId()).get();
            if (userById.getFirstName() != null && userById.getFirstName().length() > 0
                    && userById.getLastName() != null && userById.getLastName().length() > 0
                    && userById.getMail() != null && userById.getMail().length() > 0
                    && userById.getPassword() != null && userById.getPassword().length() > 0) {
                ID = user.getId();
                stub();
                model.addAttribute("user", userById);
                return "redirect:/user-info";
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        model.addAttribute("errorId", errorId);
        return "find-user";
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
        //can not load an image. I guess there's something wrong with the path to the file.
        model.addAttribute("image", "/" + user.getImage());
        return "user-info";
    }

    //Stub for 5 seconds.
    private void stub() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
