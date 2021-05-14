package com.shopping.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Social Network type enum
 *
 */
@AllArgsConstructor
@Getter
public enum DiscountType {
    FLAT("FLAT"),
    PERCENTAGE("PERCENTAGE"),
    FREEBIE("FREEBIE");

    private final String code;
}
