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

    public Auction map(AuctionDetailDTO auctionDetailDTO){
        if(Objects.isNull(auctionDetailDTO))
            return null;


        LocalDateTime startTime = LocalDateTime.now().plusSeconds(5);
        LocalDateTime  endTime = startTime.plus(auctionDetailDTO.getDuration(), ChronoUnit.SECONDS);
        return Auction.builder().startTime(startTime)
                .endTime(endTime)
                .itemCode(auctionDetailDTO.getItemCode())
                .minimumBasePrice(auctionDetailDTO.getMinimumBasePrice())
                .stepRate(auctionDetailDTO.getStepRate())
                .status(auctionDetailDTO.getStatus())
                .build();


    }
}
