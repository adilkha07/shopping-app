package com.shopping.app.dto;

import java.util.UUID;

import lombok.Value;

@Value
public class ProductQuantityDto {
    UUID product;
    int quantity;
}
