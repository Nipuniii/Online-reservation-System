package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.Offer;
import com.abcrestaurant.restaurantweb.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Offer findOfferById(Long id) {
        return offerRepository.findById(id).orElse(null);
    }
}
