package com.cars.auction.mapper;

import com.cars.auction.constants.AuctionStatus;
import com.cars.auction.dto.BidDetailDTO;
import com.cars.auction.entity.Auction;
import com.cars.auction.entity.Bids;
import com.cars.auction.exceptions.AuctionNotFoundException;
import com.cars.auction.repository.AuctionRepository;
import com.cars.auction.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class BidDetailDTOToBidMapper {

    private final AuctionRepository auctionRepository;
    private final ItemRepository itemRepository;

    public Bids map(BidDetailDTO bidDetailDTO){

        Auction auction = auctionRepository.findAuctionByItemCodeAndStatus(bidDetailDTO.getItemCode(), AuctionStatus.RUNNING.name());
        if(Objects.isNull(auction)){
            throw new AuctionNotFoundException("Auction not found for itemCode:"+bidDetailDTO.getItemCode());
        }

        return Bids.builder().bidAmount(bidDetailDTO.getBidAmount())
                .userId(bidDetailDTO.getUserId())
                .auction(auction)
                .itemCode(bidDetailDTO.getItemCode())
                .build();


    }
}
