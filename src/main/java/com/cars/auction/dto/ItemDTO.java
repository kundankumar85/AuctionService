package com.cars.auction.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class ItemDTO {

    @NotNull(message = "Item's code '${validatedValue}' can not be empty.")
    private String itemCode;
    @NotNull(message = "Item's name '${validatedValue}' can not be empty.")
    private String itemName;
}
