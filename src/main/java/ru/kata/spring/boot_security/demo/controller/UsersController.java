package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")

public class UsersController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @GetMapping("/login")
    //@RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "bootstrap/login";
    }


    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        User user = userService.findUserByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("")
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticationPrincipal = (User) authentication.getPrincipal();
        model.addAttribute("personDetails", authenticationPrincipal);
        return "bootstrap/profile";
    }
}
