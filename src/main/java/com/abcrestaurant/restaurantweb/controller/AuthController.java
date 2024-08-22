package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.model.UserRole;
import com.abcrestaurant.restaurantweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLoginPage() {
        return "auth/login"; // Points to src/main/resources/templates/auth/login.html
    }

    @PostMapping("/")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {
        User user = userService.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // Authentication successful
            if (user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.STAFF) {
                return "redirect:/admindashboard"; // Redirect to Admin Dashboard
            } else if (user.getRole() == UserRole.USER) {
                return "redirect:/home"; // Redirect to Home Page
            }
        }

        // Authentication failed
        model.addAttribute("error", "Invalid email or password");
        return "auth/login"; // Return to login page with error
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register"; // Points to src/main/resources/templates/auth/register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Register the user
        userService.registerUser(user);
        return "redirect:/"; // Redirect to login page after successful registration
    }
}
