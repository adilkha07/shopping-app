package com.shopping.app.handler;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shopping.app.dbo.UserRepository;
import com.shopping.app.entity.User;
import com.shopping.app.exception.BadRequestException;

@Service
public class UserHandler {
    private final UserRepository userRepository;

    UserHandler(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String contactNo) {
        final Optional<User> user = userRepository.findById(contactNo);
        if (user.isEmpty()) {
            throw new BadRequestException("Invalid user id");
        }
        return user.get();
    }
}
