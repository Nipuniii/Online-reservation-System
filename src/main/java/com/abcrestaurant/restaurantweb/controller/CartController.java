package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {


    @GetMapping("/cart")
    public String showCartPage() {
        return "user/cart"; // Points to src/main/resources/templates/auth/register.html
    }
}
