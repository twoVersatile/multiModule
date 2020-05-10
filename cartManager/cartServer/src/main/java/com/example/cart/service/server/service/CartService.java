package com.example.cart.service.server.service;

import com.example.cart.service.datatypes.CreateCartRequest;
import com.example.cart.service.server.entity.Cart;

public interface CartService {
    Cart getCartById(Long cartId);

    Cart createCart(CreateCartRequest createCartRequest) throws Exception;
}
