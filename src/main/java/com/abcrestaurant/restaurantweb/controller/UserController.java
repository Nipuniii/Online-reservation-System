package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/home")
    public String showLoginPage() {
        return "user/home"; // Points to src/main/resources/templates/auth/login.html
    }
}
