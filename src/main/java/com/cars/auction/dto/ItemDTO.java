package com.cars.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    @NotEmpty(message = "Item's code '${validatedValue}' can not be empty.")
    String itemCode;
    @NotEmpty(message = "Item's name '${validatedValue}' can not be empty.")
    String itemName;
}
