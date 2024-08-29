package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.Cart;
import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public List<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    // Method to clear the cart for a specific user
    public void clearCart(User user) {
        List<Cart> carts = cartRepository.findByUser(user);
        cartRepository.deleteAll(carts);
    }

    public double calculateTotalAmount(User user) {
        List<Cart> carts = findByUser(user);
        return carts.stream()
                .mapToDouble(cart -> cart.getQuantity() * cart.getMenu().getPrice())
                .sum();
    }

    // Method to find the last added cart item for a specific user based on creation time
    // Method to find the last added cart item for a specific user based on creation time
    public Cart getLastAddedCartItem(User user) {
        return cartRepository.findFirstByUserOrderByCreatedAtDesc(user);
    }

}
