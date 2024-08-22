package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.service.BranchFacilitiesService;
import com.abcrestaurant.restaurantweb.service.BranchService;
import com.abcrestaurant.restaurantweb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MenuController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private BranchFacilitiesService branchFacilitiesService;

    @GetMapping("/addmenu")
    public String AddMenuPage(Model model){
        model.addAttribute("offersList", offerService.getAllOffers());
        model.addAttribute("branchFacilitiesList", branchFacilitiesService.getAllBranchFacilities());
        return "admin/menu/addproduct";
    }


    @GetMapping("/listmenu")
    public String ListenuPage(){
        return "admin/menu/productllist";
    }
}
