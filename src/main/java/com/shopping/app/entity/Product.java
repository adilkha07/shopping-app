package com.shopping.app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * User table entity
 *
 */
@Entity
@Data
@Table(name = "product")
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @Type(type="uuid-char")
    private UUID id;

    @NotEmpty
    private String name;

    @NotEmpty
    private int price;

    @NotEmpty
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id_fk", foreignKey = @ForeignKey(name = "category_id_fk"))
    Category category_id_fk;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "product_id_fk")
    List<CartItem> cart = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "offer_id_fk", foreignKey = @ForeignKey(name = "offer_id_fk"))
    Offer offer_id_fk;

    private Product() {
    }

}
