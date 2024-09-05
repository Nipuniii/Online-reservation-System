package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.ShippingDetails;
import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.repository.ShippingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingDetailsService {

    @Autowired
    private ShippingDetailsRepository shippingDetailsRepository;

    // Method to save shipping details
    public void save(ShippingDetails shippingDetails) {
        shippingDetailsRepository.save(shippingDetails);
    }

    // Method to find the last shipping details for a user
    public ShippingDetails findLastByUser(User user) {
        return shippingDetailsRepository.findTopByUserOrderByIdDesc(user);
    }
}
