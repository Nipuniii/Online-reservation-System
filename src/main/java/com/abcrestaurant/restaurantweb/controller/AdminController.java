package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admindashboard")
    public String getDashboard() {
        return "admin/admin-dashboard"; // Renders admin-dashboard.html
    }

}
