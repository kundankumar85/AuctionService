package com.cars.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDetailDTO {

    @NotEmpty
    String itemCode;
    double minimumBasePrice;
    double stepRate;
    int duration;
    String status;

}
