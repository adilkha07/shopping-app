package com.shopping.app.handler;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shopping.app.dbo.ProductRepository;
import com.shopping.app.dto.CartDetailsDto;
import com.shopping.app.dto.ProductQuantityDto;
import com.shopping.app.entity.CartItem;
import com.shopping.app.entity.DiscountType;
import com.shopping.app.entity.Offer;
import com.shopping.app.entity.Product;
import com.shopping.app.entity.User;
import com.shopping.app.exception.BadRequestException;
import com.shopping.app.exception.ErrorMessages;

@Service
public class CartItemHandler {

    private final ProductRepository productRepository;

    protected CartItemHandler(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CartDetailsDto getCartItems(CartItem item) {
        final Product product = item.getProduct_id_fk();
        final Offer offer = product.getOffer_id_fk();
        final int pricePerItem = product.getPrice();
        final int quantity = item.getQuantity();
        final String offerInformation;
        final int price;
        if (offer != null && quantity >= offer.getMin_purchase_quantity()) {
            final DiscountType discountType = offer.getDiscount_type();
            if (discountType.equals(DiscountType.FLAT)) {
                offerInformation = "flat Rs." + offer.getOffer_value() + " off";
                price = quantity * pricePerItem - offer.getOffer_value();
            } else if (discountType.equals(DiscountType.FREEBIE)) {
                offerInformation = "you get " + offer.getOffer_value() + " more!!";
                price = quantity * pricePerItem;
            } else {
                offerInformation = offer.getOffer_value() + "% price discount";
                price = quantity * pricePerItem
                        - (quantity * pricePerItem * offer.getOffer_value() / 100);
            }
        } else {
            offerInformation = "nil";
            price = quantity * product.getPrice();
        }
        return CartDetailsDto.builder().productId(product.getId()).productName(product.getName())
                .pricePerItem(product.getPrice()).quantity(item.getQuantity())
                .offerInformation(offerInformation).price(price).build();
    }

    public CartItem mapProductsToCart(User user, ProductQuantityDto productQuantityDto) {
        final Optional<Product> productOptional = productRepository.findById(productQuantityDto.getProduct());
        if (productOptional.isEmpty()) {
            throw new BadRequestException(ErrorMessages.INVALID_PRODUCT_MSG);
        } else if (productOptional.get().getStock() < productQuantityDto.getQuantity()) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Stock unavailable!! Product is ").append(productOptional.get().getId()).append(". Available stock is ")
                    .append(productOptional.get().getStock());
            throw new BadRequestException(sb.toString());
        } else {
            return CartItem.builder().user_contact_no_fk(user).product_id_fk(productOptional.get())
                    .quantity(productQuantityDto.getQuantity())
                    .build();
        }
    }
}
