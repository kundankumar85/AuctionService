package com.cars.auction.validators.impls;

import com.cars.auction.constants.AuctionStatus;
import com.cars.auction.dto.AuctionDetailDTO;
import com.cars.auction.exceptions.ItemNotFoundException;
import com.cars.auction.exceptions.MultipleAuctionExistsException;
import com.cars.auction.repository.AuctionRepository;
import com.cars.auction.validators.AbstractValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuctionValidatorImpl extends AbstractValidator<AuctionDetailDTO, Boolean> {
    private final AuctionRepository auctionRepository;

    @Override
    public Boolean validate(AuctionDetailDTO source) {
        validateItemCode(source.getItemCode());
        validateAuction(source);
        source.setStatus(AuctionStatus.RUNNING.name());
        return Boolean.TRUE;
    }

    private void validateAuction(AuctionDetailDTO source) {
        if(Objects.equals(Boolean.TRUE,auctionRepository.existsAuctionByItemCodeAndStatus(source.getItemCode(), AuctionStatus.RUNNING.name()))){
            throw new MultipleAuctionExistsException("Auction already going on for itemCode:"+source.getItemCode());
        }
    }

    private void validateItemCode(String itemCode) {
        if(Objects.equals(Boolean.FALSE,itemCodeExists(itemCode))){
            throw new ItemNotFoundException("Item Code "+itemCode+" not found.");
        }
    }
}
