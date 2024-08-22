package com.abcrestaurant.restaurantweb.repository;

import com.abcrestaurant.restaurantweb.model.BranchFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchFacilitiesRepository extends JpaRepository<BranchFacilities, Long> {
}
