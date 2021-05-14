package com.shopping.app.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;

/**
 * 
 * composite key Id class of BrandTeam entity
 */

@SuppressWarnings("serial")
@AllArgsConstructor
public class UserProductId implements Serializable {
    @SuppressWarnings("unused")
    private User user_contact_no_fk;

    @SuppressWarnings("unused")
    private Product product_id_fk;

    @SuppressWarnings("unused")
    private UserProductId() {

    }
}
