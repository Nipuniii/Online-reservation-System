package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.Menu;
import com.abcrestaurant.restaurantweb.service.BranchFacilitiesService;
import com.abcrestaurant.restaurantweb.service.BranchService;
import com.abcrestaurant.restaurantweb.service.MenuService;
import com.abcrestaurant.restaurantweb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String listMenuPage(Model model) {
        List<Menu> menuItems = menuService.getAllMenuItems(); // Fetch all menu items
        model.addAttribute("menuItems", menuItems); // Add them to the model
        return "admin/menu/productllist";
    }


    @GetMapping("/menu")
    public String showAllBranches(Model model) {
        List<Branch> branches = branchService.findAll(); // Fetch all branches
        Map<Branch, List<Menu>> branchMenuMap = new HashMap<>();

        for (Branch branch : branches) {
            List<Menu> menuItems = menuService.findByBranch(branch); // Fetch menu items for each branch
            branchMenuMap.put(branch, menuItems);
        }

        model.addAttribute("branchMenuMap", branchMenuMap);
        return "user/menu"; // Path to your Thymeleaf template
    }


    @GetMapping("/editmenu/{id}")
    public String editMenuPage(@PathVariable("id") Long id, Model model) {
        Menu menu = menuService.findById(id);
        if (menu == null) {
            return "redirect:/error"; // Redirect to an error page or handle the not found case
        }
        model.addAttribute("menu", menu);
        model.addAttribute("offersList", offerService.getAllOffers());
        model.addAttribute("branchesList", branchService.getAllBranches());
        return "admin/menu/editproduct";
    }


    @PostMapping("/editmenu")
    public String updateMenu(
            @RequestParam("id") Long id,
            @RequestParam("product_name") String productName,
            @RequestParam("branch_id") Long branchId,
            @RequestParam("price") Double price,
            @RequestParam("offer_id") Long offerId,
            @RequestParam("availability") String availability,
            @RequestParam("description") String description,
            @RequestParam(value = "image_1", required = false) MultipartFile image1) {

        Menu menu = menuService.findById(id); // Fetch the existing menu
        if (menu != null) {
            menu.setProductName(productName);
            menu.setBranch(branchService.findBranchById(branchId));
            menu.setPrice(price);
            menu.setOffer(offerService.findOfferById(offerId));
            menu.setAvailability(availability);
            menu.setDescription(description);

            if (image1 != null && !image1.isEmpty()) {
                try {
                    // Handle image upload similarly to the save method
                    String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                    File directory = new File(IMAGE_DIR + datePath);

                    if (!directory.exists() && !directory.mkdirs()) {
                        System.err.println("Failed to create directory: " + directory.getAbsolutePath());
                    }

                    String fileName = image1.getOriginalFilename();
                    if (fileName != null) {
                        File file = new File(directory, fileName);
                        image1.transferTo(file);

                        String filePath = "/images/" + datePath + "/" + fileName;
                        menu.setImage1(filePath);
                    } else {
                        System.err.println("Filename is null");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            menuService.saveMenu(menu); // Save the updated menu
        }

        return "redirect:/listmenu";
    }



    @PostMapping("/deleteproduct/{id}")
    public String deleteMenu(@PathVariable("id") Long id) {
        Menu menu = menuService.findById(id);
        if (menu != null) {
            menuService.deleteMenu(id); // Call a method to delete the menu item
        }
        return "redirect:/listmenu"; // Redirect to the menu list page
    }

}
