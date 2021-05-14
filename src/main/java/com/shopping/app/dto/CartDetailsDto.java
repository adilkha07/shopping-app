package com.shopping.app.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CartDetailsDto {
    UUID productId;
    String productName;
    int quantity;
    int pricePerItem;
    String offerInformation;
    int price;
}
