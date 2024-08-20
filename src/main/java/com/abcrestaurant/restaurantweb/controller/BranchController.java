package com.abcrestaurant.restaurantweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BranchController {
    @GetMapping("/addbranch")
    public String AddFacilitiesPage(){
        return "admin/branch/addbranch";
    }

    @GetMapping("/listbranch")
    public String ListFacilitiesPage(){
        return "admin/branch/listbranch";
    }


    @GetMapping("/addbranchfacilities")
    public String AddBranchFacilitiesPage(){
        return "admin/branch/branch&facilities";
    }
}
