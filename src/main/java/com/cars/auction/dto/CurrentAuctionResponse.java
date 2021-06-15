package com.cars.auction.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrentAuctionResponse {
    List<CurrentAuctionDTO> currentAuctionDTOList;
}
