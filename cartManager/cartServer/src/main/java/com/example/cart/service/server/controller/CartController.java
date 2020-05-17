package com.example.cart.service.server.controller;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.example.cart.service.datatypes.CartResponse;
import com.example.cart.service.datatypes.CreateCartRequest;
import com.example.cart.service.datatypes.ItemDetail;
import com.example.cart.service.server.entity.Cart;
import com.example.cart.service.server.service.CartService;
import com.example.cart.service.server.service.ItemService;
import com.example.cart.service.server.util.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@Slf4j
@Api(value = "/")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Metered(name = "getCartWithId", absolute = true)
    @Transactional
    public ResponseEntity getCart(
        @PathVariable(value = "cartId") Long cartId
    ) throws Exception {
        Cart cart = cartService.getCartById(cartId);
        CartResponse cartResponse = new CartResponse(cartId, cart.getUserId(), cart.getCartStatus(), cart.getCreatedAt());

        return new ResponseEntity(cartResponse, HttpStatus.OK);

    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Metered(name = "createCart", absolute = true)
    public ResponseEntity createCart(
        @RequestBody @ApiParam(value = "Create cart request", required = true)
        @Valid CreateCartRequest createCartRequest
    ) throws Exception {
        Cart cart = cartService.createCart(createCartRequest);
        CartResponse cartResponse = new CartResponse(cart.getId(), cart.getUserId(), cart.getCartStatus(), cart.getCreatedAt());

        return new ResponseEntity(cartResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Metered(name = "getCartOfUser", absolute = true)
    @Transactional
    public ResponseEntity userCartDetails(
        @PathVariable(value = "userId") String userId
    ) throws Exception {

        List<Cart> userCarts = cartService.userCartDetails(userId);

        List<CartResponse> cartResponse = Mapper.parse(userCarts);

        return new ResponseEntity(cartResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/item/{cartId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Metered(name = "addItem", absolute = true)
    public ResponseEntity addItemToCart(
        @RequestBody @ApiParam(value = "ItemDetails", required = true)
        @Valid ItemDetail itemDetail,
        @PathVariable(value = "cartId") String cartId
    ) throws Exception {

        itemService.addToCart(itemDetail, Long.parseLong(cartId));

        return new ResponseEntity(HttpStatus.OK);
    }

}
