package com.shopping.app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Category table entity
 *
 */
@Entity
@Data
@Table(name = "category")
@AllArgsConstructor
@Builder
public class Category {

    @Id
    private UUID id;

    @NotEmpty
    private String name;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "category_id_fk")
    List<Product> products = new ArrayList<>();

    private Category() {
    }

}
