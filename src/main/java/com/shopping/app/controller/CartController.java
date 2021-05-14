package com.shopping.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.app.dbo.CartRepository;
import com.shopping.app.dbo.ProductRepository;
import com.shopping.app.dbo.UserRepository;
import com.shopping.app.dto.AddProductsToCartDto;
import com.shopping.app.dto.CartDetailsDto;
import com.shopping.app.entity.CartItem;
import com.shopping.app.entity.User;
import com.shopping.app.handler.CartItemHandler;
import com.shopping.app.handler.UserHandler;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemHandler cartItemHandler;
    private final UserHandler userHandler;

    @Autowired
    CartController(final UserRepository userRepository, final CartRepository cartRepository,
                   final ProductRepository productRepository, final CartItemHandler cartItemHandler,
                   final UserHandler userHandler) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemHandler = cartItemHandler;
        this.userHandler = userHandler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToCart(
            @Valid @RequestBody final AddProductsToCartDto addProductsToCartDto) {
        final User user = userHandler.getUser(addProductsToCartDto.getUserContactNo());
        final List<CartItem> cartItems = addProductsToCartDto.getProductQuantityList().stream()
                .map(productQuantity -> cartItemHandler.mapProductsToCart(user, productQuantity))
                .collect(Collectors.toList());
        cartRepository.saveAll(cartItems);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CartDetailsDto> getCart(
            @Valid @RequestParam final String userContactNo) {
        final User user = userHandler.getUser(userContactNo);
        List<CartItem> cartItems = cartRepository.findAllByUserId(user.getContact_no());
        if (cartItems.size() == 0) {
            return null;
        } else {
            return cartItems.stream().map(cartItem -> cartItemHandler.getCartItems(cartItem))
                    .collect(Collectors.toList());
        }
    }

}
