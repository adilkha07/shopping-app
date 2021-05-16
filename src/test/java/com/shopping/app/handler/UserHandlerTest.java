package com.shopping.app.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shopping.app.dbo.UserRepository;
import com.shopping.app.entity.User;
import com.shopping.app.exception.BadRequestException;

@ExtendWith(MockitoExtension.class)
public class UserHandlerTest {

    @Mock
    UserRepository userRepository;
    UserHandler userHandler;

    @BeforeEach
    void setup() {
        userHandler = new UserHandler(userRepository);
    }

    @Test
    void getUserBadRequestExceptionTest() {
        when(userRepository.findById(any(String.class)))
                .thenReturn(Optional.ofNullable(any(User.class)));

        assertThrows(BadRequestException.class, () -> {
            userHandler.getUser("91-9742527600");
        });
    }

    @Test
    void getUserTest() {
        when(userRepository.findById(any(String.class)))
                .thenReturn(
                        Optional.of(User.builder().contact_no("91-9742527600").name("Harry").build()));
        final User user = userHandler.getUser("91-9742527600");
        assertEquals("Harry", user.getName());

    }
}
