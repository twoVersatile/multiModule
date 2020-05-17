package com.example.cart.service.server.service.impl;

import com.example.cart.service.datatypes.CartStatus;
import com.example.cart.service.datatypes.ItemDetail;
import com.example.cart.service.server.entity.Cart;
import com.example.cart.service.server.entity.Item;
import com.example.cart.service.server.repository.CartRepository;
import com.example.cart.service.server.repository.ItemRepository;
import com.example.cart.service.server.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void addToCart(ItemDetail itemDetail, Long cartId) throws Exception {
        Cart cart = cartRepository.findOne(cartId);

        if (cart == null) {
            throw new Exception("Cart doesn't exist");
        }

        if (cart.getCartStatus().equals(CartStatus.CLOSED)) {
            throw new Exception("Cart status is closed.");
        }

        Item item = new Item();
        item.setQuantity(itemDetail.getQuantity());
        item.setName(itemDetail.getName());
        item.setPrice(itemDetail.getPrice());
        item.setCartId(cart);

        itemRepository.save(item);
    }
}
