package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.Facilities;
import com.abcrestaurant.restaurantweb.service.BranchService;
import com.abcrestaurant.restaurantweb.service.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FacilitiesController {

    @Autowired
    private FacilitiesService facilitiesService;
    @Autowired
    private BranchService branchService;
    @GetMapping("/addfacilities")
    public String showAddFacilitiesPage() {
        return "admin/facilities/addfacilities";
    }

    @GetMapping("/listfacilities")
    public String showListFacilitiesPage(Model model) {
        // You might want to add facilities to the model for listing
         List<Facilities> facilitiesList = facilitiesService.getAllFacilities();
         model.addAttribute("facilitiesList", facilitiesList);
        return "admin/facilities/listfacilities";
    }
    @PostMapping("/deletefacilities/{id}")
    public String deleteFacilities(@PathVariable("id") Long id, Model model) {
        facilitiesService.deleteFacilitiesById(id);
        // Optionally add a success message or redirect
        model.addAttribute("message", "Facilities deleted successfully");
        return "redirect:/listfacilities"; // Redirect to the list page after deletion
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




//    fetch facilities and branch to view
    @GetMapping("/addbranchfacilities")
    public String addBranchFacilitiesPage(Model model) {
        List<Facilities> facilitiesList = facilitiesService.getAllFacilities();
        List<Branch> branchesList = branchService.getAllBranches();  // Fetch all branches
        model.addAttribute("facilitiesList", facilitiesList);
        model.addAttribute("branchesList", branchesList);  // Add branches to the model
        return "admin/branch/branch&facilities";
    }


//  save to table facilities and branch
    @PostMapping("/addbranchfacilities")
    public String saveBranchFacilities(@RequestParam("branch_id") Long branchId,
                                       @RequestParam("facilities") List<Long> facilitiesIds,
                                       Model model) {
        // Validate the inputs (e.g., check if branchId and facilitiesIds are not null)

        // Call the service method to save the data
        branchService.saveBranchFacilities(branchId, facilitiesIds);

        // Redirect to a success page or back to the form
        return "redirect:/addbranchfacilities";  // Change the redirect page as needed
    }


}
