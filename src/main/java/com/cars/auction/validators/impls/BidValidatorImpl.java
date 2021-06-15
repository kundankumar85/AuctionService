package com.cars.auction.validators.impls;

import com.cars.auction.constants.AuctionStatus;
import com.cars.auction.dto.BidDetailDTO;
import com.cars.auction.entity.Auction;
import com.cars.auction.entity.Bids;
import com.cars.auction.exceptions.AuctionNotFoundException;
import com.cars.auction.exceptions.BidNotValidException;
import com.cars.auction.repository.BidRepository;
import com.cars.auction.service.AuctionService;
import com.cars.auction.validators.AbstractValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class BidValidatorImpl extends AbstractValidator<BidDetailDTO, Boolean> {
    private final AuctionService auctionService;
    private final BidRepository bidRepository;

    @Override
    @Transactional
    public Boolean validate(BidDetailDTO source) {
        validateAuctionForBid(source);
        validateBidDetail(source);
        return Boolean.TRUE;
    }

    private void validateBidDetail(BidDetailDTO bidDetailDTO) {
        Auction auction = auctionService.findAuctionForItemCodeAndStatus(bidDetailDTO.getItemCode(), AuctionStatus.RUNNING.name());
        if(Objects.isNull(auction)){
            throw new AuctionNotFoundException("Currently no auction is running for itemCode"+bidDetailDTO.getItemCode());
        }

        double stepRate = auction.getStepRate();

        Bids maxBidsAmount = bidRepository.findFirstByAuctionAuctionIdOrderByBidAmountDesc(auction.getAuctionId());
        double nextBidMinValue = (Objects.isNull(maxBidsAmount) || maxBidsAmount.getBidAmount() == 0 )?auction.getMinimumBasePrice():(maxBidsAmount.getBidAmount()+stepRate);

        if(bidDetailDTO.getBidAmount() < nextBidMinValue){
            throw new BidNotValidException("Bids amount "+bidDetailDTO.getBidAmount()+" should be more or equal to "+(maxBidsAmount.getBidAmount() + stepRate));
        }
    }

    private void validateAuctionForBid(BidDetailDTO  bidDetailDTO){
        boolean isExits = auctionService.isExists(bidDetailDTO.getItemCode(), AuctionStatus.RUNNING.name());
        if(!isExits){
            throw new AuctionNotFoundException("Currently no auction is running for itemCode"+bidDetailDTO.getItemCode());
        }

    }
}
