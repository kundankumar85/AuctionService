package com.cars.auction.repository;

import com.cars.auction.entity.Bids;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository  extends CrudRepository<Bids,Integer> {

    Bids findFirstByAuctionAuctionIdOrderByBidAmountDesc(Integer auctionId);

}
