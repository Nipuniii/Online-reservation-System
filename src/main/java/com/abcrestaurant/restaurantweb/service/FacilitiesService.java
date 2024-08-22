package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.Facilities;
import com.abcrestaurant.restaurantweb.repository.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitiesService {

    @Autowired
    private FacilitiesRepository facilitiesRepository;

    public Facilities saveFacilities(Facilities facilities) {
        return facilitiesRepository.save(facilities);
    }

    public List<Facilities> getAllFacilities() {
        return facilitiesRepository.findAll();
    }

}
