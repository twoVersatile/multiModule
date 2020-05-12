package com.example.cart.service.server.util;

import com.example.cart.service.datatypes.CartResponse;
import com.example.cart.service.server.entity.Cart;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<CartResponse> parse(List<Cart> userCart) {

        List<CartResponse> cartResponse = new ArrayList<>();

        for(Cart cart : userCart) {
            CartResponse response = new CartResponse(cart.getId(),cart.getUserId(),cart.getCartStatus(),cart.getCreatedAt());
            cartResponse.add(response);
        }
        return cartResponse;
    }
}
