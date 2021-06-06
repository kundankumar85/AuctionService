package com.cars.auction.repository;

import com.cars.auction.entity.Auction;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction,Integer> {

    boolean existsAuctionByItemCodeAndStatus(String itemCode, String status);
}
