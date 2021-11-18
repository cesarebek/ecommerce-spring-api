package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.dto.CartItemDto;
import com.cezarybek.ecommerce.model.Cart;
import com.cezarybek.ecommerce.service.CartService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addItemToCart(@RequestBody CartItemDto item) throws NotFoundException {
        return cartService.addItemToCart(item);
    }

    @DeleteMapping("/remove/{productId}")
    public Cart removeItemFromCart(@PathVariable long productId) throws NotFoundException {
        return cartService.removeProductFromCart(productId);
    }

}
