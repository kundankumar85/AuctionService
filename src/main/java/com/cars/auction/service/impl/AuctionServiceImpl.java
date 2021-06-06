package com.cars.auction.service.impl;

import com.cars.auction.dto.AuctionDetailDTO;
import com.cars.auction.entity.Auction;
import com.cars.auction.mapper.AuctionDetailsToAuctionMapper;
import com.cars.auction.repository.AuctionRepository;
import com.cars.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionDetailsToAuctionMapper auctionDetailsToAuctionMapper;


    @Override
    public Auction startAuction(AuctionDetailDTO auctionDetailDTO) {
        Auction auction = auctionDetailsToAuctionMapper.mapAuctionDetailsToAuction(auctionDetailDTO);
        log.info("Saving auction details");
        return auctionRepository.save(auction);
    }
}
