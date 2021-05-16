package com.shopping.app.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductQuantityDto {
    @NotNull
    UUID product;
    @NotNull
    int quantity;
}
