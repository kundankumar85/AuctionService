package com.cars.auction.service.impl;

import com.cars.auction.dto.AuctionDetailDTO;
import com.cars.auction.dto.CurrentAuctionDTO;
import com.cars.auction.entity.Auction;
import com.cars.auction.entity.Bids;
import com.cars.auction.mapper.AuctionDetailsToAuctionMapper;
import com.cars.auction.repository.AuctionRepository;
import com.cars.auction.repository.BidRepository;
import com.cars.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;
    private final AuctionDetailsToAuctionMapper auctionDetailsToAuctionMapper;



    @Override
    public Auction startAuction(AuctionDetailDTO auctionDetailDTO) {
        Auction auction = auctionDetailsToAuctionMapper.map(auctionDetailDTO);
        log.info("Saving auction details");
        return auctionRepository.save(auction);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deactivateAuction(Integer auctionId, String status) {
        auctionRepository.deactivateAuction(auctionId,status);
    }

    @Override
    public List<CurrentAuctionDTO> findAuctions(String status) {
        List<Auction> auctionList = auctionRepository.findAuctionByStatus(status);
        return auctionList.stream().map(auction -> {
            CurrentAuctionDTO currentAuctionDTO = new CurrentAuctionDTO();
            currentAuctionDTO.setItemCode(auction.getItemCode());
            currentAuctionDTO.setStepRate(auction.getStepRate());
            Bids maxBidsAmount = bidRepository.findFirstByAuctionAuctionIdOrderByBidAmountDesc(auction.getAuctionId());
            currentAuctionDTO.setHighestBidAmount(Objects.isNull(maxBidsAmount)?0:maxBidsAmount.getBidAmount());
            return currentAuctionDTO;
        }).collect(Collectors.toList());
    }


    @Override
    public Auction findAuctionForItemCodeAndStatus(String itemCode, String status) {
        return auctionRepository.findAuctionByItemCodeAndStatus(itemCode,status);
    }

    @Override
    public boolean isExists(String itemCode, String status){
        return auctionRepository.existsAuctionByItemCodeAndStatus(itemCode,status);
    }
}
