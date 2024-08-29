package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.model.UserRole;
import com.abcrestaurant.restaurantweb.service.CustomerQueryService;
import com.abcrestaurant.restaurantweb.service.MenuService;
import com.abcrestaurant.restaurantweb.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffController {
    @Autowired
    private CustomerQueryService customerQueryService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/staffdashboard")
    public String viewstaffdashboard(HttpSession session, Model model) {
        if (!isStaff(session)) {
            return "redirect:/home"; // Redirect to home or error page if not staff
        }
        long totalQueryCount = customerQueryService.getAllCustomerQueries().size();
        model.addAttribute("totalQueryCount", totalQueryCount);

        long totalReservationCount = reservationService.getAllReservations().size();
        model.addAttribute("totalReservationCount", totalReservationCount);


        return "staff/staffdashboard"; // Renders staff-dashboard.html
    }


    private boolean isStaff(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        return loggedInUser != null && (loggedInUser.getRole() == UserRole.ADMIN || loggedInUser.getRole() == UserRole.STAFF);
    }
}
