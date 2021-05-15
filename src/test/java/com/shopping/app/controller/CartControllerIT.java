package com.shopping.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.shopping.app.dbo.CartRepository;
import com.shopping.app.entity.CartItem;
import com.shopping.app.entity.Product;
import com.shopping.app.entity.User;
import com.shopping.app.exception.ExceptionHandlers;
import com.shopping.app.handler.CartItemHandler;
import com.shopping.app.handler.UserHandler;

@WebMvcTest(excludeAutoConfiguration = {OAuth2ResourceServerAutoConfiguration.class})
@ContextConfiguration(classes = {CartController.class, ExceptionHandlers.class})
@ExtendWith(SpringExtension.class)
public class CartControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartItemHandler cartItemHandler;
    @MockBean
    private UserHandler userHandler;
    @MockBean
    private CartRepository cartRepository;

    private final String URI = "/cart";

    @Test
    public void addtoCartTest() throws Exception {
        //To do read payload from fixtures
        final String PAYLOAD = "{\n"
                + "    \"userContactNo\" : \"91-9742527000\",\n"
                + "    \"productQuantityList\": [\n"
                + "\n"
                + "        {\n"
                + "        \"product\": \"a7b8972b-71d7-45ff-bca5-78ef40297461\",\n"
                + "        \"quantity\": 5\n"
                + "        }\n"
                + "    ]\n"
                + "}";

        when(userHandler.getUser(any(String.class)))
                .thenReturn(User.builder().contact_no("91-9742527600").name("Harry").build());
        Product product = Product.builder().id(UUID.fromString("3336bb4e-7eb2-4859-8ce8-27c7556f89e7")).name("apple").price(5).stock(10)
                .build();
        CartItem cartItem = CartItem.builder().product_id_fk(product).quantity(5).build();
        when(cartItemHandler.mapProductsToCart(any(), any()))
                .thenReturn(cartItem);
        mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .content(PAYLOAD).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
    }
    //To do getCartItems test
}
