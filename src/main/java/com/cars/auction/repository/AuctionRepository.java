package com.cars.auction.repository;

import com.cars.auction.entity.Auction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuctionRepository extends CrudRepository<Auction,Integer> {

    boolean existsAuctionByItemCodeAndStatus(String itemCode, String status);
    @Modifying
    @Query("update Auction auction set auction.status = :status where auction.id = :id ")
    void deactivateAuction(@Param("id") Integer in,@Param("status") String status);

    List<Auction> findAuctionByStatus(String status);

    Auction findAuctionByItemCodeAndStatus(String itemCode,String status);



}
