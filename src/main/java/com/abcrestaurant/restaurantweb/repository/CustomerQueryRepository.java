package com.abcrestaurant.restaurantweb.repository;

import com.abcrestaurant.restaurantweb.model.CustomerQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerQueryRepository extends JpaRepository<CustomerQuery, Long> {
}
