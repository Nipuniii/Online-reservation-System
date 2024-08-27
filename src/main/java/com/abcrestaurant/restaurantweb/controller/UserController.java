package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.service.BranchFacilitiesService;
import com.abcrestaurant.restaurantweb.service.BranchService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private BranchFacilitiesService branchFacilitiesService;
    @Autowired
    private BranchService branchService;
    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        // Retrieve the logged-in user from the session
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("username", loggedInUser.getName()); // Pass username to the view
        }

        // Retrieve branch and facilities data
        List<Branch> branches = branchService.getAllBranches();

        // Map to store facilities for each branch
        Map<Long, List<String>> branchFacilitiesMap = new HashMap<>();
        for (Branch branch : branches) {
            List<String> facilities = branchFacilitiesService.getFacilitiesForBranch(branch.getId());
            branchFacilitiesMap.put(branch.getId(), facilities);
        }

        // Add branch data to the model
        model.addAttribute("branches", branches);
        model.addAttribute("branchFacilitiesMap", branchFacilitiesMap);

        return "user/home"; // Ensure this view exists
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
