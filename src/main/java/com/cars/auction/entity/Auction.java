package com.cars.auction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auction {

    @Id
    @GeneratedValue
    @Column(name="auction_id")
    private int auctionId;
    @Column(name="minimum_base_price")
    private double minimumBasePrice;
    @Column(name="step_rate")
    private double stepRate;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    @Column(name="start_time")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    @Column(name="end_time")
    private LocalDateTime endTime;
    @Column(name="status")
    private String status;
    @Column(name="item_code")
    private String itemCode;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "auction")
    @JsonIgnoreProperties("auction")
    private Set<Bids> bids;


}
