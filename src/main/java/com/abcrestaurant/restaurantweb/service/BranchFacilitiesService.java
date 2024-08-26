package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.BranchFacilities;
import com.abcrestaurant.restaurantweb.model.Facilities;
import com.abcrestaurant.restaurantweb.repository.BranchFacilitiesRepository;
import com.abcrestaurant.restaurantweb.repository.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchFacilitiesService {

    @Autowired
    private BranchFacilitiesRepository branchFacilitiesRepository;

    @Autowired
    private FacilitiesRepository facilitiesRepository;

    public List<BranchFacilities> getAllBranchFacilities() {
        return branchFacilitiesRepository.findAll();
    }

    public List<String> getFacilitiesForBranch(Long branchId) {
        List<BranchFacilities> branchFacilities = branchFacilitiesRepository.findByBranchId(branchId);
        return branchFacilities.stream()
                .map(branchFacility -> facilitiesRepository.findById(branchFacility.getFacilityId())
                        .map(Facilities::getFacilitiesName)
                        .orElse(null))
                .collect(Collectors.toList());
    }
}
