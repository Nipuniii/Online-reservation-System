package com.abcrestaurant.restaurantweb.repository;

import com.abcrestaurant.restaurantweb.model.ShippingDetails;
import com.abcrestaurant.restaurantweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, Long> {
    // Method to find the last shipping details for a user
    ShippingDetails findTopByUserOrderByIdDesc(User user);
}
