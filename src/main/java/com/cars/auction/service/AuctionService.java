package com.cars.auction.service;

import com.cars.auction.dto.AuctionDetailDTO;
import com.cars.auction.entity.Auction;

public interface AuctionService {

    Auction startAuction(AuctionDetailDTO auctionDetailDTO);
}
