package com.cars.auction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
    @Temporal(TemporalType.DATE)
    private Date startTime;
    @Temporal(TemporalType.DATE)
    private Date endTime;
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_code",referencedColumnName = "itemCode")
    private Item item;

    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "bid_id",referencedColumnName = "id")
    private List<Bid> userBids;


}
