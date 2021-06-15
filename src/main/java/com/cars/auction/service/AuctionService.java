package com.cars.auction.service;

import com.cars.auction.dto.AuctionDetailDTO;
import com.cars.auction.dto.CurrentAuctionDTO;
import com.cars.auction.entity.Auction;

import java.util.List;

public interface AuctionService {

    Auction startAuction(AuctionDetailDTO auctionDetailDTO);

    void deactivateAuction(Integer auctionId, String name);

    List<CurrentAuctionDTO> findAuctions(String status);
    Auction findAuctionForItemCodeAndStatus(String itemCode, String status);
    boolean isExists(String itemCode, String status);
}
