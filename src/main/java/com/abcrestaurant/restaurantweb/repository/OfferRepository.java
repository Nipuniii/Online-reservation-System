package com.abcrestaurant.restaurantweb.repository;

import com.abcrestaurant.restaurantweb.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
