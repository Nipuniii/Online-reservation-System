package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login"; // Points to src/main/resources/templates/auth/login.html
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register"; // Points to src/main/resources/templates/auth/register.html
    }
}
