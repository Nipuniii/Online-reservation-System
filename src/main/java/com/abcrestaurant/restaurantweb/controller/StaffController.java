package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class StaffController {
    @GetMapping("/staffdashboard")
    public String viewstaffdashboard() {
        return "staff/staffdashboard"; // Renders admin-dashboard.html
    }


}
