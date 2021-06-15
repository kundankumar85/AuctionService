package com.cars.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAuctionDTO {

    @NotEmpty
    String itemCode;
    double highestBidAmount;
    double stepRate;
}
