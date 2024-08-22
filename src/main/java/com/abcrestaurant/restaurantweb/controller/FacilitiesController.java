package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Facilities;
import com.abcrestaurant.restaurantweb.service.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FacilitiesController {

    @Autowired
    private FacilitiesService facilitiesService;

    @GetMapping("/addfacilities")
    public String showAddFacilitiesPage() {
        return "admin/facilities/addfacilities";
    }

    @GetMapping("/listfacilities")
    public String showListFacilitiesPage(Model model) {
        // You might want to add facilities to the model for listing
        // List<Facilities> facilitiesList = facilitiesService.getAllFacilities();
        // model.addAttribute("facilitiesList", facilitiesList);
        return "admin/facilities/listfacilities";
    }

    @PostMapping("/addfacilities")
    public String addFacilities(@RequestParam("facilitiesName") String facilitiesName,
                                @RequestParam("shortDescription") String shortDescription,
                                Model model) {
        Facilities facilities = new Facilities();
        facilities.setFacilitiesName(facilitiesName);
        facilities.setShortDescription(shortDescription);

        facilitiesService.saveFacilities(facilities);

        // Optionally add a success message or redirect
        model.addAttribute("message", "Facilities added successfully");
        return "redirect:/listfacilities"; // Redirect to the list page after submission
    }
}
