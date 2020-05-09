package com.example.cart.service.server.service.impl;

import com.example.cart.service.datatypes.CartStatus;
import com.example.cart.service.datatypes.CreateCartRequest;
import com.example.cart.service.server.entity.Cart;
import com.example.cart.service.server.repository.CartRepository;
import com.example.cart.service.server.service.CartService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;


    @Override
    public Cart getCartById(Long cartId) {

        return cartRepository.findByIdEquals(cartId);
    }

    @Override
    public Cart createCart(CreateCartRequest createCartRequest) {
        Cart cart = new Cart();

        cart.setCartStatus(CartStatus.OPENED);
        cart.setUserId(createCartRequest.getUserId());
        cart.setCreatedAt(new DateTime());
        cart.setCreatedBy(createCartRequest.getUserId());

        return cartRepository.save(cart);
    }

}
