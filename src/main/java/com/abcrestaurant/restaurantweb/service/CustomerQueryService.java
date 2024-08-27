package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.CustomerQuery;
import com.abcrestaurant.restaurantweb.repository.CustomerQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerQueryService {

    @Autowired
    private CustomerQueryRepository customerQueryRepository;

    public CustomerQuery saveCustomerQuery(CustomerQuery query) {
        query.setCreatedAt(LocalDateTime.now());
        return customerQueryRepository.save(query);
    }

    public List<CustomerQuery> getAllCustomerQueries() {
        return customerQueryRepository.findAll();
    }

    // New method to get a specific CustomerQuery by ID
    public CustomerQuery getCustomerQueryById(Long id) {
        Optional<CustomerQuery> customerQueryOptional = customerQueryRepository.findById(id);
        return customerQueryOptional.orElse(null); // Return the query or null if not found
    }


    public void deleteCustomerQueryById(Long id) {
        customerQueryRepository.deleteById(id);  // Deletes the query from the repository
    }
}
