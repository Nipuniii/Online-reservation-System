package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Cart;
import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.service.CartService;
import com.abcrestaurant.restaurantweb.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private CartService cartService;

    @GetMapping("/payment")
    public String showPaymentPage(HttpSession session, Model model) {
        // Retrieve the logged-in user from the session
        User user = (User) session.getAttribute("loggedInUser"); // Use the attribute name that matches your session setup

        if (user == null) {
            // If user is not authenticated, redirect to login or another appropriate page
            return "redirect:/"; // Redirect to login page if user is not authenticated
        }

        // Fetch the last added cart item for the user
        Cart lastAddedCartItem = cartService.getLastAddedCartItem(user);
        if (lastAddedCartItem != null) {
            model.addAttribute("lastAddedProduct", lastAddedCartItem.getMenu());
            model.addAttribute("lastAddedProductQuantity", lastAddedCartItem.getQuantity()); // Add quantity to model
        }

        // Fetch all cart items for the authenticated user
        List<Cart> cartItems = cartService.findByUser(user);
        // Calculate the total amount for the cart
        double totalAmount = cartService.calculateTotalAmount(user);

        // Add cart items and total amount to the model
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);

        // Return the view for the payment page
        return "user/payment"; // Return the Thymeleaf template path
    }


    @PostMapping("/processPayment")
    public String processPayment(HttpSession session,
                                 @RequestParam("cardholderName") String cardholderName,
                                 @RequestParam("totalAmount") double totalAmount,
                                 @RequestParam("productName") String productName,
                                 @RequestParam("quantity") int quantity) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/";
        }

        // Assume the payment process is successful
        String emailSubject = "Payment Successful";
        String emailBody = String.format("Dear %s,\n\nYour payment was successful.\n\nDetails:\nCardholder Name: %s\nTotal Price: %.2f\nProduct Name: %s\nQuantity: %d\n\nThank you for your purchase!",
                user.getName(), cardholderName, totalAmount, productName, quantity);

        // Send the email
        emailService.sendEmail(user.getEmail(), emailSubject, emailBody);

        // Redirect to a confirmation page or any other page
        return "redirect:/menu";
    }
}
