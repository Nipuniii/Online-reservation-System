package com.abcrestaurant.restaurantweb.service;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.BranchFacilities;
import com.abcrestaurant.restaurantweb.model.Menu;
import com.abcrestaurant.restaurantweb.repository.BranchFacilitiesRepository;
import com.abcrestaurant.restaurantweb.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Branch findBranchById(Long id) {
        return branchRepository.findById(id).orElse(null);
    }
    public void saveBranchFacilities(Long branchId, List<Long> facilitiesIds) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        // Clear existing facilities for this branch
        branchFacilitiesRepository.deleteAllByBranch(branch);

        for (Long facilityId : facilitiesIds) {
            BranchFacilities branchFacilities = new BranchFacilities();
            branchFacilities.setBranch(branch);
            branchFacilities.setFacilityId(facilityId);
            branchFacilitiesRepository.save(branchFacilities);
        }
    }
    public Branch findById(Long branchId) {
        return branchRepository.findById(branchId).orElse(null);
    }
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

}
