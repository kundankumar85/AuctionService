package com.cars.auction.service.impl;

import com.cars.auction.dto.BidDetailDTO;
import com.cars.auction.entity.Bids;
import com.cars.auction.mapper.BidDetailDTOToBidMapper;
import com.cars.auction.repository.BidRepository;
import com.cars.auction.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final BidDetailDTOToBidMapper bidDetailDTOToBidMapper;

    @Transactional
    public void placeBid(BidDetailDTO bidDetailDTO){
        Bids bids = bidDetailDTOToBidMapper.map(bidDetailDTO);
        bidRepository.save(bids);
    }
}
