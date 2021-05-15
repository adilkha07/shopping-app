package com.shopping.app.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductQuantityDto {
    UUID product;
    int quantity;
}
