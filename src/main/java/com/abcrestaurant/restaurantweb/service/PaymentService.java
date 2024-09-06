package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.Payment;
import com.abcrestaurant.restaurantweb.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
