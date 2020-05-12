package com.example.cart.service.server.service;

import com.example.cart.service.datatypes.CreateCartRequest;
import com.example.cart.service.server.entity.Cart;

import java.util.List;

public interface CartService {
    Cart getCartById(Long cartId);

    Cart createCart(CreateCartRequest createCartRequest) throws Exception;

    List<Cart> userCartDetails(String userId);
}
