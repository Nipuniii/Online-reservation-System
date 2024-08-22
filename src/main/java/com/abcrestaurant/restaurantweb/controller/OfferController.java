package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Offer;
import com.abcrestaurant.restaurantweb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OfferController {

    @Autowired
    private OfferService offerService;

    @RequestMapping("/addoffer")
    public String addOfferPage() {
        return "admin/offer/addoffer"; // Ensure this view exists
    }

    @PostMapping("/saveoffer")
    public String saveOffer(Offer offer) {
        offerService.saveOffer(offer);
        return "redirect:/addoffer"; // Redirect to the form page or a success page
    }
}
