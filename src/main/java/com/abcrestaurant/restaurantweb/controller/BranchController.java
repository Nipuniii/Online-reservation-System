package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.repository.BranchRepository;
import com.abcrestaurant.restaurantweb.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BranchController {

    // Define the image directory location
    private static final String IMAGE_DIR = "C:/images/";
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private BranchService branchService;

    // View for adding a branch
    @GetMapping("/addbranch")
    public String addBranchPage(Model model) {
        model.addAttribute("branch", new Branch());
        return "admin/branch/addbranch"; // Ensure this view exists
    }

    // Add branch with image upload
    @PostMapping("/branches")
    public String saveBranch(
            @Valid @ModelAttribute Branch branch,
            @RequestParam("branch_image_1") MultipartFile branchImageFile,
            BindingResult bindingResult,
            Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "admin/branch/addbranch"; // Return to form if there are errors
        }

        // Handle the image upload
        if (!branchImageFile.isEmpty()) {
            try {
                // Create a directory structure based on the current date
                String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                File directory = new File(IMAGE_DIR + datePath);

                // Create the directory if it doesn't exist
                if (!directory.exists()) {
                    if (!directory.mkdirs()) {
                        System.err.println("Failed to create directory: " + directory.getAbsolutePath());
                        // You could add additional error handling here
                    }
                }

                // Save the file to the directory
                String fileName = branchImageFile.getOriginalFilename();
                if (fileName != null) {
                    File file = new File(directory, fileName);
                    branchImageFile.transferTo(file);

                    // Save the relative path to the database
                    String filePath = "/images/" + datePath + "/" + fileName;
                    branch.setBranchImage(filePath); // Save the image path to the branch object
                } else {
                    System.err.println("Filename is null");
                    // You could handle the error more gracefully here
                }

            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error during file transfer
            }
        }

        // Save the branch to the database
        branchService.saveBranch(branch);

        // Redirect to a page listing branches or a success page
        return "redirect:/listbranch";
    }

    // View for listing branches
    @GetMapping("/listbranch")
    public String listBranchesPage() {
        return "admin/branch/listbranch"; // Ensure this view exists
    }



}
