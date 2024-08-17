package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/home")
    public String showHomePage() {
        return "user/home"; // Points to src/main/resources/templates/auth/login.html
    }

    @GetMapping("/contactus")
    public String showContactPage() {
        return "user/contactus"; // Points to src/main/resources/templates/auth/login.html
    }

    @GetMapping("/aboutus")
    public String showAboutPage(){
        return  "user/aboutus";
    }
}
