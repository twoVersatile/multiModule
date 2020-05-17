package com.example.cart.service.server.service;

import com.example.cart.service.datatypes.ItemDetail;

public interface ItemService {
    void addToCart(ItemDetail itemDetail, Long cartId) throws Exception;
}
