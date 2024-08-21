package com.abcrestaurant.restaurantweb.repository;

import com.abcrestaurant.restaurantweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}