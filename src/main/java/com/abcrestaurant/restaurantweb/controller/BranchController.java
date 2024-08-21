package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BranchController {
    @Autowired
    private BranchService branchService;


//    view add branch
    @GetMapping("/addbranch")
    public String addBranchPage(Model model) {
        model.addAttribute("branch", new Branch());
        return "admin/branch/addbranch"; // Ensure this view exists
    }

    //   add branch
    @PostMapping("/branches")
    public String saveBranch(@Valid @ModelAttribute Branch branch, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/branch/addbranch"; // Return to form if there are errors
        }
        branchService.saveBranch(branch);
        return "redirect:/listbranch"; // Redirect to a list of branches or another success page
    }



    @GetMapping("/listbranch")
    public String listBranchesPage() {
        return "admin/branch/listbranch"; // Ensure this view exists
    }

    @GetMapping("/addbranchfacilities")
    public String addBranchFacilitiesPage() {
        return "admin/branch/branch&facilities"; // Ensure this view exists
    }
}
