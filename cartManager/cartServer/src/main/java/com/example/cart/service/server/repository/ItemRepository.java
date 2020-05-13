package com.example.cart.service.server.repository;

import com.example.cart.service.server.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query(value = "select i from Item i where i.cartId = :cartId")
    List<Item> itemsForCartId(@Param("cartId")String cartId);
}
