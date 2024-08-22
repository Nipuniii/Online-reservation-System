package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.BranchFacilities;
import com.abcrestaurant.restaurantweb.repository.BranchFacilitiesRepository;
import com.abcrestaurant.restaurantweb.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;


    @Autowired
    private BranchFacilitiesRepository branchFacilitiesRepository;

    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll(); // Fetch all branches from the repository
    }



//    save branches and facilities
    public void saveBranchFacilities(Long branchId, List<Long> facilitiesIds) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        for (Long facilityId : facilitiesIds) {
            BranchFacilities branchFacilities = new BranchFacilities();
            branchFacilities.setBranch(branch);
            branchFacilities.setFacilityId(facilityId);
            branchFacilitiesRepository.save(branchFacilities);
        }
    }



}
