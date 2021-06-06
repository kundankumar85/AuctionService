package com.cars.auction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime endTime;
    private String status;
    private String itemCode;

    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "bid_id",referencedColumnName = "id")
    private List<Bid> userBids;


}
