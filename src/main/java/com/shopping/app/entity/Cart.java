package com.shopping.app.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@IdClass(UserProductId.class)
@Table(name = "cart")
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_contact_no_fk", foreignKey = @ForeignKey(name = "user_contact_no_fk"))
    User user_contact_no_fk;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id_fk", foreignKey = @ForeignKey(name = "product_id_fk"))
    Product product_id_fk;
    @NotNull
    int quantity;

    private Cart() {
    }

}
