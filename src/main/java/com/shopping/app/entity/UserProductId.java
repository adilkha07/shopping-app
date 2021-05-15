package com.shopping.app.entity;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.Type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 
 * composite key Id class of BrandTeam entity
 */

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProductId implements Serializable {
    @SuppressWarnings("unused")
    private String user_contact_no_fk;

    @Type(type = "uuid-char")
    private UUID product_id_fk;

}
