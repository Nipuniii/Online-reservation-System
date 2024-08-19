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

    @GetMapping("/availbility")
    public String showAvailabilityPage(){
        return  "user/availabilitycheck";
    }

    @GetMapping("/reservation")
    public String showReservationPage(){
        return  "user/reservation";
    }

    @GetMapping("/menu")
    public String showaMenuPage(){
        return  "user/menu";
    }
}
