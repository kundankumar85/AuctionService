package com.cars.auction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {

    @Id
    @GeneratedValue
    @Column(name = "bid_id")
    private Integer bidId;
    private String userId;
    private double bidAmount;
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_code",referencedColumnName = "itemCode")
    private Item item;

    @ManyToOne
    private Auction auction;
}
