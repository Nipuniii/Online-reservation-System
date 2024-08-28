package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.model.UserRole;
import com.abcrestaurant.restaurantweb.service.BranchService;
import com.abcrestaurant.restaurantweb.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BranchService branchService;

    @GetMapping("/admindashboard")

    public String getDashboard(HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home";
        }
        return "admin/admin-dashboard";
    }




//view addNew Member
    @GetMapping("/addnewmember")
    public String getNewMember(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("branches", branchService.getAllBranches()); //
        // Pass enum values to the view
        model.addAttribute("roles", UserRole.values());
        return "admin/customers/addnewmember"; // Renders admin-dashboard.html
    }
//save addnew member in admin panel
    @PostMapping("/saveuser")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", UserRole.values());
            model.addAttribute("branches", branchService.getAllBranches());
            return "admin/customers/addnewmember";
        }
        // Save the user to the database
        userService.saveUser(user);
        return "redirect:/success"; // Redirect to a success page or list of users
    }


    private boolean isAdmin(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        return loggedInUser != null && loggedInUser.getRole() == UserRole.ADMIN;
    }

}


