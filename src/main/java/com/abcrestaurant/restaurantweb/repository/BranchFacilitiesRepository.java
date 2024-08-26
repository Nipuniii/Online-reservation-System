package com.abcrestaurant.restaurantweb.repository;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.BranchFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchFacilitiesRepository extends JpaRepository<BranchFacilities, Long> {
    void deleteAllByBranch(Branch branch);

    // Custom query method to find BranchFacilities by branchId
    List<BranchFacilities> findByBranchId(Long branchId);
}
