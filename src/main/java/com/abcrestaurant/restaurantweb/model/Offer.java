package com.abcrestaurant.restaurantweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double offerPercentage;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOfferPercentage() {
        return offerPercentage;
    }

    public void setOfferPercentage(Double offerPercentage) {
        this.offerPercentage = offerPercentage;
    }
}
