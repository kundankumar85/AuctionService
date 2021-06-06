package com.cars.auction.mapper;

import com.cars.auction.dto.AuctionDetailDTO;
import com.cars.auction.entity.Auction;
import com.cars.auction.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class AuctionDetailsToAuctionMapper {
    private final ItemRepository itemRepository;

    public Auction mapAuctionDetailsToAuction(AuctionDetailDTO auctionDetailDTO){
        if(Objects.isNull(auctionDetailDTO))
            return null;

        Auction auction = new Auction();
        auction.setItemCode(auctionDetailDTO.getItemCode());
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime  endTime = startTime.plus(auctionDetailDTO.getDuration(), ChronoUnit.HOURS);
        auction.setStartTime(startTime);
        auction.setEndTime(endTime);
        auction.setMinimumBasePrice(auctionDetailDTO.getMinimumBasePrice());
        auction.setStepRate(auctionDetailDTO.getStepRate());
        auction.setStatus(auctionDetailDTO.getStatus());
        return auction;

    }
}
