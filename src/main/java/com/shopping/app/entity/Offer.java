package com.shopping.app.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Offer table entity
 *
 */
@Entity
@Data
@Table(name = "offer")
@AllArgsConstructor
@Builder
public class Offer {
    @Id
    @Type(type = "uuid-char")
    UUID id;

    @NotNull
    int min_purchase_quantity;

    @NotNull
    int offer_value;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(60) CHECK (discount_type IN ('FLAT', 'PERCENTAGE', 'FREEBIE'))")
    private DiscountType discount_type;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "offer_id_fk")
    private List<Product> products;

    private Offer() {
    }

}
