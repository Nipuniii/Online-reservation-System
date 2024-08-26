package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BranchService branchService;
    @GetMapping("/home")
    public String homePage(Model model) {
        List<Branch> branches = branchService.getAllBranches();
        model.addAttribute("branches", branches);
        return "user/home"; // Ensure this view exists
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

//    @GetMapping("/menu")
//    public String showaMenuPage(){
//        return  "user/menu";
//    }



}
