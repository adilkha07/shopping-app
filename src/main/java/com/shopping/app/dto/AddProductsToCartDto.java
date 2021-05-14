package com.shopping.app.dto;

import java.util.List;

import lombok.Value;

@Value
public class AddProductsToCartDto {
    String userContactNo;
    List<ProductQuantityDto> productQuantityList;
}
