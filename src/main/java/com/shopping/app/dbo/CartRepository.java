package com.shopping.app.dbo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.app.entity.User;

public interface CartRepository extends JpaRepository<User, String> {

}
