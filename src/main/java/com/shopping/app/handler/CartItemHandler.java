package com.shopping.app.handler;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shopping.app.dbo.ProductRepository;
import com.shopping.app.dto.ProductQuantityDto;
import com.shopping.app.entity.CartItem;
import com.shopping.app.entity.Product;
import com.shopping.app.entity.User;
import com.shopping.app.exception.BadRequestException;
import com.shopping.app.exception.ErrorMessages;

@Service
public class CartItemHandler {

    private final ProductRepository productRepository;

    CartItemHandler(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CartItem mapProductsToCart(User user, ProductQuantityDto productQuantityDto) {
        Optional<Product> productOptional = productRepository.findById(productQuantityDto.getProduct());
        if (productOptional.isEmpty()) {
            throw new BadRequestException(ErrorMessages.INVALID_PRODUCT_MSG);
        } else if (productOptional.get().getStock() < productQuantityDto.getQuantity()) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Stock unavailable Product is ").append(productOptional.get().getId()).append("available stock is ")
                    .append(productOptional.get().getStock());
            throw new BadRequestException(sb.toString());
        } else {
            return CartItem.builder().user_contact_no_fk(user).product_id_fk(productOptional.get())
                    .quantity(productQuantityDto.getQuantity())
                    .build();
        }
    }
}
