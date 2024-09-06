package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Cart;
import com.abcrestaurant.restaurantweb.model.Payment;
import com.abcrestaurant.restaurantweb.model.ShippingDetails;
import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.service.CartService;
import com.abcrestaurant.restaurantweb.service.EmailService;
import com.abcrestaurant.restaurantweb.service.PaymentService;
import com.abcrestaurant.restaurantweb.service.ShippingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ShippingDetailsService shippingDetailsService; // Inject ShippingDetailsService

    @GetMapping("/payment")
    public String showPaymentPage(HttpSession session, Model model) {
        // Retrieve the logged-in user from the session
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/";
        }

        // Fetch the last added cart item for the user
        Cart lastAddedCartItem = cartService.getLastAddedCartItem(user);
        if (lastAddedCartItem != null) {
            model.addAttribute("lastAddedProduct", lastAddedCartItem.getMenu());
            model.addAttribute("lastAddedProductQuantity", lastAddedCartItem.getQuantity());
        }

        // Fetch all cart items for the authenticated user
        List<Cart> cartItems = cartService.findByUser(user);
        double totalAmount = cartService.calculateTotalAmount(user);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);

        return "user/payment";
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

        // Fetch the last added shipping details for the user
        ShippingDetails shippingDetails = shippingDetailsService.findLastByUser(user);

        // Save payment details
        Payment payment = new Payment(shippingDetails, productName, totalAmount / quantity, quantity, totalAmount);
        paymentService.save(payment);

        // Send confirmation email
        String emailSubject = "Payment Successful";
        String emailBody = String.format("Dear %s,\n\nYour payment was successful.\n\nDetails:\nCardholder Name: %s\nTotal Price: %.2f\nProduct Name: %s\nQuantity: %d\n\nThank you for your purchase!",
                user.getName(), cardholderName, totalAmount, productName, quantity);
        emailService.sendEmail(user.getEmail(), emailSubject, emailBody);

        return "redirect:/menu";
    }

    @GetMapping("/paymentlist")
    public String showPaymentPage(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "admin/payment/paymentlist"; // Points to src/main/resources/templates/auth/register.html
    }
}
