package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class Auth {


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @GetMapping("/logout")
    public String showLogoutPage() {
        return "logout";
    }
}