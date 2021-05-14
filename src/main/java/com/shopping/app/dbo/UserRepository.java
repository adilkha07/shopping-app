package com.shopping.app.dbo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.app.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
