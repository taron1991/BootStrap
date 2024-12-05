package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/user")

public class UsersController {
    private final UserService user;

    @Autowired
    public UsersController(UserService user) {
        this.user = user;
    }

    @GetMapping("/")
    public String showUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", user.findById(id));
        return "userD/user";
    }
}
