package com.shopping.app.dbo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopping.app.entity.CartItem;
import com.shopping.app.entity.UserProductId;

public interface CartRepository extends JpaRepository<CartItem, UserProductId> {

    @Query("select b from CartItem b where b.user_contact_no_fk.contact_no = :#{#userContactNo}")
    List<CartItem> findAllByUserId(String userContactNo);
}
