package com.shopping.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import com.shopping.app.dto.ProductQuantityDto;
import com.shopping.app.entity.CartItem;
import com.shopping.app.entity.DiscountType;
import com.shopping.app.entity.Offer;
import com.shopping.app.entity.Product;
import com.shopping.app.entity.User;
import com.shopping.app.exception.BadRequestException;
import com.shopping.app.exception.ErrorMessages;
import com.shopping.app.handler.CartItemHandler;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemHandler cartItemHandler;

    @Autowired
    CartController(final UserRepository userRepository, final CartRepository cartRepository,
                   final ProductRepository productRepository, final CartItemHandler cartItemHandler) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemHandler = cartItemHandler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToCart(
            @Valid @RequestBody final AddProductsToCartDto addProductsToCartDto) {
        final Optional<User> user = userRepository.findById(addProductsToCartDto.getUserContactNo());
        if (user.isEmpty()) {
            throw new BadRequestException("Invalid user id");
        }
        final List<CartItem> cartItems = addProductsToCartDto.getProductQuantityList().stream()
                .map(productQuantity -> cartItemHandler.mapProductsToCart(user.get(), productQuantity))
                .collect(Collectors.toList());
        cartRepository.saveAll(cartItems);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CartDetailsDto> getCart(
            @Valid @RequestParam final String userContactNo) {
        final Optional<User> user = userRepository.findById(userContactNo);
        if (user.isEmpty()) {
            throw new BadRequestException("Invalid user id");
        }
        List<CartDetailsDto> cartDetailsList = new ArrayList<CartDetailsDto>();
        List<CartItem> cartItems = cartRepository.findAllByUserId(user.get().getContact_no());
        for (CartItem item : cartItems) {
            Product product = item.getProduct_id_fk();
            Offer offer = product.getOffer_id_fk();
            int minPurchaseQuantity = offer.getMin_purchase_quantity();
            DiscountType discount_type = offer.getDiscount_type();
            String discount;
            int finalPrice;
            if (item.getQuantity() >= minPurchaseQuantity) {
                if (discount_type.equals(DiscountType.FLAT)) {
                    discount = "flat Rs. " + offer.getOffer_value() + " off";
                    finalPrice = item.getQuantity() * product.getPrice() - offer.getOffer_value();
                } else if (discount_type.equals(DiscountType.FREEBIE)) {
                    discount = "you get " + offer.getOffer_value() + "more!!";
                    finalPrice = item.getQuantity() * product.getPrice();

                } else {
                    discount = offer.getOffer_value() + "% price discount";
                    finalPrice = item.getQuantity() * product.getPrice()
                            - (item.getQuantity() * product.getPrice() * offer.getOffer_value() / 100);
                }
            } else {
                discount = "nil";
                finalPrice = product.getPrice();
            }
            cartDetailsList.add(CartDetailsDto.builder().productId(product.getId()).productName(product.getName())
                    .pricePerItem(product.getPrice()).quantity(item.getQuantity())
                    .discountInformation(discount).finalPrice(finalPrice).build());
        }
        return cartDetailsList;
    }

}
