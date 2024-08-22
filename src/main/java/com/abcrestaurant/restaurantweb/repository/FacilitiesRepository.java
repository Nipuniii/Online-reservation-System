package com.abcrestaurant.restaurantweb.repository;

import com.abcrestaurant.restaurantweb.model.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilitiesRepository extends JpaRepository<Facilities, Long> {
}
