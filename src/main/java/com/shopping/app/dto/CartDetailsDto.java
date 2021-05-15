package com.shopping.app.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CartDetailsDto {
    @NotNull
    UUID productId;
    @NotEmpty
    String productName;
    @NotNull
    int quantity;
    @NotNull
    int pricePerItem;
    @NotEmpty
    String offerInformation;
    @NotNull
    int price;
}
