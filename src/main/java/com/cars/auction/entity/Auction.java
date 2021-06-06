package com.cars.auction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auction {

    @Id
    @GeneratedValue
    private int id;
    private double minimumBasePrice;
    private double stepRate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String itemCode;

    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "bid_id",referencedColumnName = "id")
    private List<Bid> userBids;


}
