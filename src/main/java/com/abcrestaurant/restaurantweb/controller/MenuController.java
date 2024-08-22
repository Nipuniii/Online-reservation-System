package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Menu;
import com.abcrestaurant.restaurantweb.service.BranchFacilitiesService;
import com.abcrestaurant.restaurantweb.service.BranchService;
import com.abcrestaurant.restaurantweb.service.MenuService;
import com.abcrestaurant.restaurantweb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MenuController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private BranchFacilitiesService branchFacilitiesService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private MenuService menuService;

    // Base directory for image storage (adjust path as needed)
    private static final String IMAGE_DIR = "C:/images/"; // Example for local development, adjust as needed

    @GetMapping("/addmenu")
    public String addMenuPage(Model model) {
        model.addAttribute("offersList", offerService.getAllOffers());
        model.addAttribute("branchFacilitiesList", branchFacilitiesService.getAllBranchFacilities());
        model.addAttribute("branchesList", branchService.getAllBranches());
        return "admin/menu/addproduct";
    }

    @PostMapping("/addmenu")
    public String saveMenu(
            @RequestParam("product_name") String productName,
            @RequestParam("branch_id") Long branchId,
            @RequestParam("price") Double price,
            @RequestParam("offer_id") Long offerId,
            @RequestParam("availability") String availability,
            @RequestParam("description") String description,
            @RequestParam("image_1") MultipartFile image1) {

        Menu menu = new Menu();
        menu.setProductName(productName);
        menu.setBranch(branchService.findBranchById(branchId));
        menu.setPrice(price);
        menu.setOffer(offerService.findOfferById(offerId));
        menu.setAvailability(availability);
        menu.setDescription(description);

        // Handle the file upload
        if (!image1.isEmpty()) {
            try {
                // Create directory structure by date
                String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                File directory = new File(IMAGE_DIR + datePath);

                // Create directories if they don't exist
                if (!directory.exists()) {
                    if (!directory.mkdirs()) {
                        System.err.println("Failed to create directory: " + directory.getAbsolutePath());
                        // Handle directory creation error
                    }
                }

                // Save the file
                String fileName = image1.getOriginalFilename();
                if (fileName != null) {
                    File file = new File(directory, fileName);
                    image1.transferTo(file);

                    // Save the relative path to the database
                    String filePath = "/images/" + datePath + "/" + fileName;
                    menu.setImage1(filePath); // Save the path in the database
                } else {
                    System.err.println("Filename is null");
                    // Handle filename null error
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle file saving error
            }
        } else {
            System.err.println("No file uploaded");
            // Handle no file uploaded error
        }

        // Debug information
        System.out.println("Saving menu: " + menu);
        menuService.saveMenu(menu);

        return "redirect:/addmenu";
    }

    @GetMapping("/listmenu")
    public String listMenuPage() {
        return "admin/menu/productlist";
    }
}
