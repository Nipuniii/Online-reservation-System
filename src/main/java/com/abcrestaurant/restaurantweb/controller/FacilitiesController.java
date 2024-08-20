package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacilitiesController {
    @GetMapping("/addfacilities")
    public String AddFacilitiesPage(){
        return "admin/facilities/addfacilities";
    }

    @GetMapping("/listfacilities")
    public String ListFacilitiesPage(){
        return "admin/facilities/listfacilities";
    }
}
