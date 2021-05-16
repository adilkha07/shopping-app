package com.shopping.app.dbo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.app.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
