package com.cars.auction.timer;

import com.cars.auction.constants.AuctionStatus;
import com.cars.auction.service.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Slf4j
@Component
@Scope("prototype")
public class AuctionTimerTask extends TimerTask {

    @Autowired
    private AuctionService auctionService;

    private Integer auctionId;

    @Override
    public void run() {
        log.info("Auction {} completed.",getAuctionId());
        auctionService.deactivateAuction(getAuctionId(), AuctionStatus.OVER.name());
        cancel();
        log.info("Auction {} status is over now .",getAuctionId());

    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }
}
