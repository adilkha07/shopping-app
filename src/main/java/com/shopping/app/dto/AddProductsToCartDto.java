package com.shopping.app.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
public class AddProductsToCartDto {
    @NotEmpty
    String userContactNo;
    @NotNull
    List<ProductQuantityDto> productQuantityList;
}
