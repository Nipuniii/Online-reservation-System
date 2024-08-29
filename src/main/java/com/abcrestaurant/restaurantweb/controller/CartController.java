package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Cart;
import com.abcrestaurant.restaurantweb.model.Menu;
import com.abcrestaurant.restaurantweb.model.ShippingDetails;
import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.service.CartService;
import com.abcrestaurant.restaurantweb.service.MenuService;
import com.abcrestaurant.restaurantweb.service.ShippingDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ShippingDetailsService shippingDetailsService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/cart")
    public String showCartPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            List<Cart> carts = cartService.findByUser(loggedInUser);
            model.addAttribute("carts", carts);
        } else {
            model.addAttribute("error", "You need to log in to view your cart.");
        }
        return "user/checkout"; // Points to src/main/resources/templates/user/cart.html
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("menuId") Long menuId,
                            @RequestParam("quantity") Integer quantity,
                            HttpSession session,
                            Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            Menu menu = menuService.findById(menuId);
            if (menu != null) {
                Cart cart = new Cart(loggedInUser, menu, quantity);
                cartService.save(cart);
                return "redirect:/checkout"; // Redirect to the cart page to see the updated cart
            } else {
                model.addAttribute("error", "Menu item not found");
            }
        } else {
            model.addAttribute("error", "User not logged in");
        }
        return "redirect:/home"; // Redirect to home if there is an error
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("phone") String phone,
                           @RequestParam("city") String city,
                           @RequestParam("streetAddress") String streetAddress,
                           @RequestParam("postCode") String postCode,
                           @RequestParam("email") String email,
                           HttpSession session,
                           Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            ShippingDetails shippingDetails = new ShippingDetails(firstName, lastName, phone, city, streetAddress, postCode, email, loggedInUser);
            shippingDetailsService.save(shippingDetails);
            // Optionally clear the cart or handle other post-checkout logic here
            return "redirect:/payment"; // Redirect to an order confirmation page
        } else {
            model.addAttribute("error", "User not logged in");
            return "redirect:/home";
        }
    }
}
