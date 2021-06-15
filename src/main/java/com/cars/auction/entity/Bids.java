package com.cars.auction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bids {

    @Id
    @GeneratedValue
    @Column(name = "bid_id")
    private Integer bidId;
    @Column(name="user_id")
    private String userId;
    @Column(name="bid_amount")
    private double bidAmount;
    @Column(name="item_code")
    private String itemCode;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="auction_id", nullable=false)
    private Auction auction;

}
