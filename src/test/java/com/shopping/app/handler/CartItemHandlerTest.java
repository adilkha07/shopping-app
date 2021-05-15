package com.shopping.app.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shopping.app.dbo.CartRepository;
import com.shopping.app.dbo.ProductRepository;
import com.shopping.app.dto.CartDetailsDto;
import com.shopping.app.dto.ProductQuantityDto;
import com.shopping.app.entity.CartItem;
import com.shopping.app.entity.DiscountType;
import com.shopping.app.entity.Offer;
import com.shopping.app.entity.Product;
import com.shopping.app.entity.User;
import com.shopping.app.exception.BadRequestException;

@ExtendWith(MockitoExtension.class)
public class CartItemHandlerTest {

    @Mock
    ProductRepository productRepository;
    CartRepository cartRepository;
    CartItemHandler cartItemHandler;

    @BeforeEach
    void setup() {
        cartItemHandler = new CartItemHandler(productRepository, cartRepository);
    }

    @Test
    void mapProductsToCartExceptionTest() {
        User user = User.builder().contact_no("91-9742527600").name("Harry").build();
        ProductQuantityDto productQuantityDto = ProductQuantityDto.builder()
                .product(UUID.fromString("3336bb4e-7eb2-4859-8ce8-27c7556f89e7")).quantity(10).build();
        when(productRepository.findById(any(UUID.class)))
                .thenReturn(Optional.ofNullable(any(Product.class)));

        assertThrows(BadRequestException.class, () -> {
            cartItemHandler.mapProductsToCart(user, productQuantityDto);
        });
    }

    @Test
    void mapProductsToCartStockExceptionTest() {
        User user = User.builder().contact_no("91-9742527600").name("Harry").build();
        ProductQuantityDto productQuantityDto = ProductQuantityDto.builder()
                .product(UUID.fromString("3336bb4e-7eb2-4859-8ce8-27c7556f89e7")).quantity(10).build();
        Product product = Product.builder().id(UUID.fromString("3336bb4e-7eb2-4859-8ce8-27c7556f89e7")).name("apple").price(5).stock(5)
                .build();
        when(productRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(product));
        assertThrows(BadRequestException.class, () -> {
            cartItemHandler.mapProductsToCart(user, productQuantityDto);
        });
    }

    @Test
    void getCartItemsTest() {
        // no discount test
        Product product = Product.builder().id(UUID.fromString("3336bb4e-7eb2-4859-8ce8-27c7556f89e7")).name("apple").price(5).stock(10)
                .build();
        CartItem cartItem = CartItem.builder().product_id_fk(product).quantity(5).build();
        CartDetailsDto cartDetials = cartItemHandler.getCartItems(cartItem);
        assertEquals(25, cartDetials.getPrice());
        assertEquals("nil", cartDetials.getOfferInformation());
        //with discount test
        Offer offer = Offer.builder().id(UUID.fromString("3336bb4e-7eb2-4859-8ce8-27c7556f89e8")).discount_type(DiscountType.FLAT)
                .min_purchase_quantity(2).offer_value(10).build();
        product.setOffer_id_fk(offer);
        CartDetailsDto cartDetials2 = cartItemHandler.getCartItems(cartItem);
        assertEquals(15, cartDetials2.getPrice());
        assertEquals("flat Rs.10 off", cartDetials2.getOfferInformation());
        //To do write tests for freebie and discount percentage        
    }

}
