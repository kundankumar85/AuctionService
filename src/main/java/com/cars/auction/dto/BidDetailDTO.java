package com.cars.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidDetailDTO {
    @NotEmpty
    String itemCode;
    @NotEmpty
    String userId;
    double bidAmount;
}
