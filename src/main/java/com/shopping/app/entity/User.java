package com.shopping.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * User table entity
 *
 */
@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String contact_no;

    @NotEmpty
    private String name;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user_contact_no_fk")
    List<CartItem> cart = new ArrayList<>();

    private User() {
    }

}
