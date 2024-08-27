package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.CustomerQuery;
import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.service.CustomerQueryService;
import com.abcrestaurant.restaurantweb.service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactusController {

    @Autowired
    private CustomerQueryService customerQueryService;

    @Autowired
    private EmailService emailService; // Assuming you have an EmailService for sending emails

    @GetMapping("/contactus")
    public String showContactPage(Model model) {
        model.addAttribute("customerQuery", new CustomerQuery());
        return "user/contactus";
    }

    @PostMapping("/contactus")
    public String saveCustomerQuery(@ModelAttribute CustomerQuery customerQuery, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            customerQuery.setUser(loggedInUser);
            customerQueryService.saveCustomerQuery(customerQuery);
            return "redirect:/home";
        }

        return "redirect:/contactus";
    }

    @GetMapping("/query")
    public String viewQueryPage(Model model) {
        List<CustomerQuery> customerQueries = customerQueryService.getAllCustomerQueries();
        model.addAttribute("customerQueries", customerQueries);
        return "staff/query/querylist"; // Make sure "staff/query/querylist.html" exists
    }

    @GetMapping("/queryresponse/{id}")
    public String viewResponsePage(@PathVariable("id") Long id, Model model) {
        CustomerQuery query = customerQueryService.getCustomerQueryById(id);
        if (query != null) {
            model.addAttribute("email", query.getEmail());
        }
        return "staff/query/sendresponse"; // sendresponse.html template
    }


    @PostMapping("/sendresponse")
    public String sendResponse(@RequestParam("email") String email, @RequestParam("body") String body) {
        emailService.sendEmail(email, "Response to Your Query", body);
        return "redirect:/query";
    }

    @PostMapping("/query/delete/{id}")
    public String deleteQuery(@PathVariable("id") Long id) {
        customerQueryService.deleteCustomerQueryById(id);
        return "redirect:/query"; // Redirect back to the query list page after deletion
    }

}
