package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.BranchFacilities;
import com.abcrestaurant.restaurantweb.repository.BranchFacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchFacilitiesService {

    @Autowired
    private BranchFacilitiesRepository branchFacilitiesRepository;

    public List<BranchFacilities> getAllBranchFacilities() {
        return branchFacilitiesRepository.findAll();
    }
}
