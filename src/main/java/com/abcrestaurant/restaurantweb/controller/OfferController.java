package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferController {
    @GetMapping("/addoffer")
    public String AddFacilitiesPage(){
        return "admin/offer/addoffer";
    }
}
