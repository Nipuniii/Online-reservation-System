package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.ShippingDetails;
import com.abcrestaurant.restaurantweb.repository.ShippingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingDetailsService {

    @Autowired
    private ShippingDetailsRepository shippingDetailsRepository;

    public void save(ShippingDetails shippingDetails) {
        shippingDetailsRepository.save(shippingDetails);
    }
}
