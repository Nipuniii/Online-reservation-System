package com.abcrestaurant.restaurantweb.controller;

import ch.qos.logback.core.model.Model;
import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.model.UserRole;
import com.abcrestaurant.restaurantweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @GetMapping("/")
    public String showLoginPage() {
        return "auth/login"; // Points to src/main/resources/templates/auth/login.html
    }



//    view register
    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register"; // Points to src/main/resources/templates/auth/register.html
    }

//    save new user in user register form
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Check if passwords match
        // Handle form submission and user registration


        // Register the user
        userService.registerUser(user);

        return "redirect:/"; // Redirect to login page after successful registration
    }
}
