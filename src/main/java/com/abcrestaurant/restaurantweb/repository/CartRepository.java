package com.abcrestaurant.restaurantweb.repository;

import com.abcrestaurant.restaurantweb.model.Cart;
import com.abcrestaurant.restaurantweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
//    void deleteAll(List<Cart> carts);
Cart findFirstByUserOrderByCreatedAtDesc(User user);

}
