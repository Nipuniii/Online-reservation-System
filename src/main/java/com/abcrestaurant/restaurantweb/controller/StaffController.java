package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.model.UserRole;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffController {

    @GetMapping("/staffdashboard")
    public String viewstaffdashboard(HttpSession session) {
        if (!isStaff(session)) {
            return "redirect:/home"; // Redirect to home or error page if not staff
        }
        return "staff/staffdashboard"; // Renders staff-dashboard.html
    }

    private boolean isStaff(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        return loggedInUser != null && (loggedInUser.getRole() == UserRole.ADMIN || loggedInUser.getRole() == UserRole.STAFF);
    }
}
