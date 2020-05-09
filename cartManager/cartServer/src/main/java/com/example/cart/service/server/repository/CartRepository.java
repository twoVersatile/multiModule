package com.example.cart.service.server.repository;

import com.example.cart.service.server.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByIdEquals(Long id);

    @Query(value = "select c from Cart c where c.userId = :userId ")
    List<Cart> findCartOfUser(@Param("userId") String userId);
}
