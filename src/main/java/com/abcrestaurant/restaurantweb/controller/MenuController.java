package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MenuController {
    @GetMapping("/addmenu")
    public String AddMenuPage(){
        return "admin/menu/addproduct";
    }


    @GetMapping("/listmenu")
    public String ListenuPage(){
        return "admin/menu/productllist";
    }
}
