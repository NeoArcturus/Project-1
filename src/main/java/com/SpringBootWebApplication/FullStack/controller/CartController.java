package com.SpringBootWebApplication.FullStack.controller;

import com.SpringBootWebApplication.FullStack.Communication.AddProductRequest;
import com.SpringBootWebApplication.FullStack.Communication.CartCreation;
import com.SpringBootWebApplication.FullStack.Communication.RemoveProductRequest;
import com.SpringBootWebApplication.FullStack.entity.Cart;
import com.SpringBootWebApplication.FullStack.exception.*;
import com.SpringBootWebApplication.FullStack.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("/userId/{id}/cart")
    public Cart getCartByUserId(@PathVariable Long id) throws CartNotFoundException {
        return service.showCartByUserId(id);
    }

    @PostMapping("/addToCart")
    public Cart addToCart(@RequestBody AddProductRequest request) throws ProductExistInTheCartException {
        return service.addToCart(request);
    }

    @PostMapping("/createCart")
    public Cart createCart(@RequestBody CartCreation cartCreation) throws UserNotFoundException {
        return service.createCart(cartCreation);
    }

    @PostMapping("/removeProduct")
    public Cart removeProduct(@RequestBody RemoveProductRequest request) throws ProductNotFoundException {
        return service.removeFromCart(request);
    }

    @PostMapping("/updateProduct")
    public Cart updateProductInCart(@RequestBody AddProductRequest request) throws ProductNotFoundException {
        return service.updateProduct(request);
    }
}
